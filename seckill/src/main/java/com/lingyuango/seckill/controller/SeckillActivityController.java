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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/seckillActivity")
@Slf4j
public class SeckillActivityController {
    private final SeckillActivityService seckillActivityService;
    private final SeckillApplicationFormService seckillApplicationFormService;
    private final PreScreeningClient preScreeningClient;
    private final PaymentClient paymentClient;

    /**
     * 初始化在Redis中秒杀活动的基本信息
     */
    @PostConstruct
    public void initActivityInfoInRedis() {
        seckillActivityService.initRedis();
    }

    /**
     * 获取秒杀活动信息
     */
    @GetMapping("/getCurrent")
    public R<SeckillActivity> getCurrent(@CookieValue("account") Integer account) {
        return R.stream()
                // 往初筛表插入记录
                .exec(() -> preScreeningClient.insert(account))
                // 获取秒杀活动
                .build(seckillActivityService::getLatest);
    }

    /**
     * 获取秒杀活动倒计时
     * @return 倒计时（单位：秒）
     */
    @GetMapping("/getCountDown")
    public R<Long> getCountDown() {
        return R.ok(seckillActivityService.getCountDown());
    }

    /**
     * 申请秒杀
     */
    @PostMapping("/apply")
    public R<Void> apply(@CookieValue("account") Integer account) {
        return R.stream()
                // 检查是否重复申请
                .check(seckillApplicationFormService.contains(account), MsgMapping.APPLY_REPEAT)
                // 插入申请信息
                .exec(() -> seckillApplicationFormService.insert(account))
                .build();
    }

    /**
     * 获取暴露的秒杀地址
     */
    @GetMapping("/getSeckillUrl")
    public R<String> getSeckillUrl(@CookieValue("account") Integer account) {
        return R.stream()
                // 检查是否申请
                .check(!seckillApplicationFormService.contains(account), MsgMapping.DOES_NOT_APPLY)
                // 检查秒杀是否还没开始
                .check(seckillActivityService.seckillNotStarted(), MsgMapping.SECKILL_NOT_STARTED)
                .exec(() -> log.info("用户id {} 获取秒杀链接", account))
                // 获取秒杀链接
                .build(() -> seckillActivityService.getLatestSeckillId().toString());
    }

    /**
     * 秒杀接口
     */
    @PostMapping("/seckill/{seckillUrl}")
    public R<Void> seckill(@CookieValue("account") Integer account,
                           @PathVariable String seckillUrl)
            throws JsonProcessingException {
        return R.stream()
                // 检查是否申请
                .check(!seckillApplicationFormService.contains(account), MsgMapping.DOES_NOT_APPLY)
                // 检查秒杀链接是否合法
                .check(seckillActivityService.isInvalidSeckillUrl(seckillUrl), MsgMapping.INVALID_SECKILL_URL)
                // 检查非法访问
                .check(!seckillActivityService.seckillNotStarted(), MsgMapping.INVALID_ACCESS)
                // 检查重复购买
                .check(seckillActivityService.hasSeckillSuccess(seckillUrl, account), MsgMapping.PURCHASE_REPEAT)
                // 检查是否售完
                .check(seckillActivityService.isSoldOut(), MsgMapping.PRODUCT_SOLD_OUT)
                // 扣库存
                .exec(() -> seckillActivityService.decreaseStorage(seckillUrl, account))
                // 异步下订单
                .exec(() -> seckillActivityService.placeOrderAsync(seckillUrl, account))
                .build();
    }

    /**
     * 测试秒杀接口
     */
    @PostMapping("/testSeckill")
    public R<Void> testSeckill(Integer account, String seckillUrl) {
        // 随机用户id
        var randCustomerId = Rand.nextInt();

        return R.stream()
                // 检查是否申请
                .check(!seckillApplicationFormService.contains(account), MsgMapping.DOES_NOT_APPLY)
                // 检查秒杀链接是否合法
                .check(seckillActivityService.isInvalidSeckillUrl(seckillUrl), MsgMapping.INVALID_SECKILL_URL)
                // 检查非法访问
                .check(!seckillActivityService.seckillNotStarted(), MsgMapping.INVALID_ACCESS)
                // 检查重复购买
                .check(seckillActivityService.hasSeckillSuccess(seckillUrl, account), MsgMapping.PURCHASE_REPEAT)
                // 检查是否售完
                .check(seckillActivityService.isSoldOut(), MsgMapping.PRODUCT_SOLD_OUT)
                // 扣库存
                .exec(() -> seckillActivityService.decreaseStorage(seckillUrl, randCustomerId))
                // 异步下订单
                .exec(() -> seckillActivityService.placeOrderAsync(seckillUrl, randCustomerId))
                .build();
    }


    /**
     * 获取订单
     */
    @GetMapping("/getOrder")
    public R<BasicOrder> getOrder(@CookieValue("account") Integer account) {
        var seckillId = seckillActivityService.getSeckillIdFromRedis();
        // 检查用户是否已经秒杀成功
        if (!seckillActivityService.hasSeckillSuccess(seckillId, account)) {
            return R.error(MsgMapping.NOT_PURCHASE);
        }

        // 请求成功直接返回订单数据
        return R.ok(seckillActivityService.getOrder(seckillId, account));
    }

    /**
     * 支付
     */
    @PostMapping("/pay")
    public R<PaymentStatus> pay(@CookieValue("account") Integer account, String orderId) {
        // 检查用户订单号正确性
        if (seckillActivityService.isInvalidOrderId(account, orderId)) {
            return R.error(MsgMapping.WRONG_ORDER_ID);
        }

        // 检测用户重复支付
        if (seckillActivityService.hasAlreadyPaid(account)) {
            return R.error(MsgMapping.PURCHASE_REPEAT);
        }

        // 请求支付
        paymentClient.requestForPay(orderId);

        // 储存错误信息的指针
        Pointer<String> errorMsgPtr = Pointer.empty();
        // 获取支付状态信息
        var paymentStatus = seckillActivityService.getPaymentStatus(
                account, orderId, errorMsgPtr);

        // 判断是否获取成功并返回
        return paymentStatus != null ? R.ok(paymentStatus) : R.error(errorMsgPtr.get());
    }
}
