package com.lingyuango.seckill.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmc.lang.ref.Pointer;
import com.jmc.net.R;
import com.jmc.util.Rand;
import com.lingyuango.seckill.client.PaymentClient;
import com.lingyuango.seckill.client.PreScreeningClient;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
import com.lingyuango.seckill.pojo.SeckillActivity;
import com.lingyuango.seckill.service.SeckillActivityService;
import com.lingyuango.seckill.service.SeckillApplicationFormService;
import com.lingyuango.seckill.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/seckillActivity")
@Slf4j
public class SeckillActivityController {
    private final TokenService tokenService;
    private final SeckillActivityService seckillActivityService;
    private final SeckillApplicationFormService seckillApplicationFormService;
    private final PreScreeningClient preScreeningClient;
    private final PaymentClient paymentClient;

    @PostMapping("/getCurrent")
    public synchronized R<SeckillActivity> getCurrent(@CookieValue(value = "token", required = false) String token) {
        var accountId = tokenService.getAccountId(token);
        if (accountId == null) {
            return R.error(MsgMapping.NOT_LOGGED_ON);
        }

        var activity = seckillActivityService.getLatest();

        // 往初筛表插入记录
        preScreeningClient.insert(accountId);

        log.info("用户id {} 获取最新的秒杀活动：{}", accountId, activity);

        return R.ok(activity);
    }

    /**
     * 获取当前秒杀活动倒计时
     * @return 倒计时（单位：秒）
     */
    @PostMapping("/getCountDown")
    public synchronized R<Long> getCountDown(@CookieValue(value = "token", required = false) String token) {
        var accountId = tokenService.getAccountId(token);
        if (accountId == null) {
            return R.error(MsgMapping.NOT_LOGGED_ON);
        }

        var activity = seckillActivityService.getLatest();
        long countDown = Duration.between(LocalDateTime.now(), activity.getStartTime()).toSeconds();
        countDown = countDown < 0 ? 0 : countDown;

        log.info("用户id {} 获取最新的秒杀活动倒计时：{} 秒", accountId, activity);

        return R.ok(countDown);
    }

    /**
     * 申请秒杀
     */
    @PostMapping("/apply")
    public synchronized R<Void> apply(@CookieValue(value = "token", required = false) String token) {
        var accountId = tokenService.getAccountId(token);
        if (accountId == null) {
            return R.error(MsgMapping.NOT_LOGGED_ON);
        }

        if (seckillApplicationFormService.contains(accountId)) {
            return R.error(MsgMapping.APPLY_REPEAT);
        }

        var insertSuccess = seckillApplicationFormService.insert(accountId);
        return insertSuccess ? R.ok().build() :
                R.error(MsgMapping.APPLY_FAILED);
    }

    /**
     * 获取暴露的秒杀地址
     */
    @PostMapping("/getSeckillUrl")
    public R<String> getSeckillUrl(@CookieValue(value = "token", required = false) String token) {
        var accountId = tokenService.getAccountId(token);
        if (accountId == null) {
            return R.error(MsgMapping.NOT_LOGGED_ON);
        }

        // 检查是否申请
        if (!seckillApplicationFormService.contains(accountId)) {
            return R.error(MsgMapping.DOES_NOT_APPLY);
        }

        var between = Duration.between(LocalDateTime.now(), seckillActivityService.getLatest().getStartTime());

        // 检查秒杀是否开始
        if (between.toMillis() > 0) {
            return R.error(MsgMapping.SECKILL_NOT_STARTED);
        }

        var seckillId = seckillActivityService.getLatestSeckillId();
        log.info("用户id {} 获取秒杀链接 {}", accountId, seckillId);

        return R.ok(seckillId.toString());
    }

    /**
     * 初始化在Redis中秒杀活动的基本信息
     */
    @PostConstruct
    public void initActivityInfoInRedis() {
        seckillActivityService.initRedis();
    }

    /**
     * 秒杀接口
     */
    @PostMapping("/seckill/{seckillUrl}")
    public R<Void> seckill(@CookieValue(value = "token", required = false) String token,
                           @PathVariable String seckillUrl)
            throws JsonProcessingException {
        // 如果售完就直接返回
        if (seckillActivityService.isSoldOut()) {
            return R.error(MsgMapping.PRODUCT_SOLD_OUT);
        }

        // 检查token
        var accountId = tokenService.getAccountId(token);
        if (accountId == null) {
            return R.error(MsgMapping.NOT_LOGGED_ON);
        }

        // 检查是否申请
        if (!seckillApplicationFormService.contains(accountId)) {
            return R.error(MsgMapping.DOES_NOT_APPLY);
        }

        // 检查秒杀链接是否合法
        if (seckillActivityService.isInvalidSeckillUrl(seckillUrl)) {
            return R.error(MsgMapping.INVALID_SECKILL_URL);
        }

        // 检查重复购买
        if (seckillActivityService.hasSeckillSuccess(seckillUrl, accountId)) {
            return R.error(MsgMapping.PURCHASE_REPEAT);
        }

        // 扣库存，如果库存不足返回失败信息
        if (!seckillActivityService.decreaseStorage(seckillUrl, accountId)) {
            return R.error(MsgMapping.PRODUCT_SOLD_OUT);
        }

        // 异步下订单
        seckillActivityService.placeOrderAsync(new BasicOrder() {{
            setSeckillId(Integer.valueOf(seckillUrl));
            setAccountId(accountId);
            setMoney(seckillActivityService.getOrderPriceFromRedis(seckillUrl));
        }});

        // 返回秒杀成功信息
        return R.ok().build();
    }

    /**
     * 测试秒杀接口
     */
    @PostMapping("/testSeckill")
    public R<Void> testSeckill(String token, String seckillUrl) {
        // 如果已经卖完就返回商品售完信息
        if (seckillActivityService.isSoldOut()) {
            return R.error(MsgMapping.PRODUCT_SOLD_OUT);
        }

        // 检查token
        var accountId = tokenService.getAccountId(token);
        if (accountId == null) {
            return R.error(MsgMapping.NOT_LOGGED_ON);
        }

        var applied = seckillApplicationFormService.contains(accountId);
        // 检查是否申请
        if (!applied) {
            return R.error(MsgMapping.DOES_NOT_APPLY);
        }

        // 检查秒杀链接是否合法
        if (seckillActivityService.isInvalidSeckillUrl(seckillUrl)) {
            return R.error(MsgMapping.INVALID_SECKILL_URL);
        }

        // 随机用户id
        var randCustomerId = Rand.nextInt();

        // 扣库存，如果库存不足返回失败信息
        if (!seckillActivityService.decreaseStorage(seckillUrl, randCustomerId)) {
            return R.error(MsgMapping.PRODUCT_SOLD_OUT);
        }

        // 获取订单金额
        var orderPrice = seckillActivityService.getOrderPriceFromRedis(seckillUrl);

        // 异步下订单
        seckillActivityService.placeOrderAsync(new BasicOrder() {{
            setSeckillId(Integer.valueOf(seckillUrl));
            setAccountId(randCustomerId);
            setMoney(orderPrice);
        }});

        // 返回秒杀成功信息
        return R.ok().build();
    }


    /**
     * 获取订单
     */
    @PostMapping("/getOrder")
    public R<BasicOrder> getOrder(@CookieValue(value = "token", required = false) String token) {
        // 检查token
        var accountId = tokenService.getAccountId(token);
        if (accountId == null) {
            return R.error(MsgMapping.NOT_LOGGED_ON);
        }

        var seckillId = seckillActivityService.getSeckillIdFromRedis();
        // 检查用户是否已经秒杀成功
        if (!seckillActivityService.hasSeckillSuccess(seckillId, accountId)) {
            return R.error(MsgMapping.NOT_PURCHASE);
        }

        // 请求成功直接返回订单数据
        return R.ok(seckillActivityService.getOrder(seckillId, accountId));
    }

    /**
     * 支付
     */
    @PostMapping("/pay")
    public R<PaymentStatus> pay(@CookieValue(value = "token", required = false) String token, String orderId) {
        // 检查token
        var accountId = tokenService.getAccountId(token);
        if (accountId == null) {
            return R.error(MsgMapping.NOT_LOGGED_ON);
        }

        // 检查用户订单号正确性
        if (seckillActivityService.isInvalidOrderId(accountId, orderId)) {
            return R.error(MsgMapping.WRONG_ORDER_ID);
        }

        // 检测用户重复支付
        if (seckillActivityService.hasAlreadyPaid(accountId)) {
            return R.error(MsgMapping.PURCHASE_REPEAT);
        }

        // 请求支付
        paymentClient.requestForPay(orderId);

        // 储存错误信息的指针
        Pointer<String> errorMsgPtr = Pointer.empty();
        // 获取支付状态信息
        var paymentStatus = seckillActivityService.getPaymentStatus(
                accountId, orderId, errorMsgPtr);

        // 判断是否获取成功并返回
        return paymentStatus != null ? R.ok(paymentStatus) : R.error(errorMsgPtr.get());
    }
}
