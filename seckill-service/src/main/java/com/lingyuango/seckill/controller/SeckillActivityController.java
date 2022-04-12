package com.lingyuango.seckill.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmc.lang.Objs;
import com.jmc.lang.Strs;
import com.jmc.lang.Threads;
import com.jmc.net.R;
import com.jmc.util.Rand;
import com.lingyuango.seckill.client.PaymentClient;
import com.lingyuango.seckill.client.PreScreeningClient;
import com.lingyuango.seckill.common.Const;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.pojo.*;
import com.lingyuango.seckill.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/seckillActivity")
@Slf4j
public class SeckillActivityController {
    private final StringRedisTemplate redisTemplate;
    private final TokenService tokenService;
    private final SeckillActivityService seckillActivityService;
    private final SeckillApplicationFormService seckillApplicationFormService;
    private final PreScreeningClient preScreeningClient;
    private final PaymentClient paymentClient;

    /**
     * 商品是否售完（默认为false）
     */
    private volatile boolean soldOut = false;

    /**
     * 获取最新规则
     */
    @PostMapping("/getRule")
    public R<SeckillActivityRule> getRule() {
        SeckillActivityRule res;
        if ((res = seckillActivityService.getRule()) == null) {
            return R.error()
                    .build();
        } else {
            return R.ok()
                    .data(res);
        }
    }

    /**
     * 获取商品信息
     */
    @PostMapping("/getProduct")
    public R<Product> getProduct() {
        var activity = seckillActivityService.getLatest();
        return R.ok()
                .data(activity.getProduct());
    }

    /**
     * 获取最新的秒杀id
     */
    @PostMapping("/getSeckillId")
    public R<Integer> getSeckillId() {
        var seckillId = seckillActivityService.getLatestSeckillId();
        return seckillId == null ? R.error().msg("Latest seckillId is null").build() : R.ok().data(seckillId);
    }

    @PostMapping("/getCurrent")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public synchronized R<SeckillActivity> getCurrent(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var activity = seckillActivityService.getLatest();

        // 往初筛表插入记录
        preScreeningClient.insert(customerId).get();

        log.info("用户id {} 获取最新的秒杀活动：{}", customerId, activity);

        return R.ok()
                .data(activity);
    }

    /**
     * 获取当前秒杀活动倒计时
     * @return 倒计时（单位：秒）
     */
    @PostMapping("/getCountDown")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public synchronized R<Long> getCountDown(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if (token == null || (customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var activity = seckillActivityService.getLatest();
        long countDown = Duration.between(LocalDateTime.now(), activity.getStartTime()).toSeconds();
        countDown = countDown < 0 ? 0 : countDown;

        log.info("用户id {} 获取最新的秒杀活动倒计时：{} 秒", customerId, activity);

        return R.ok()
                .data(countDown);
    }

    /**
     * 申请秒杀
     */
    @PostMapping("/apply")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public synchronized R<Void> apply(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        if (seckillApplicationFormService.contains(customerId)) {
            return R.error()
                    .msg(MsgMapping.APPLY_REPEAT)
                    .build();
        }

        var insertSuccess = seckillApplicationFormService.insert(customerId);
        return insertSuccess ? R.ok().msg(MsgMapping.APPLY_SUCCESS).build() :
                R.error().msg(MsgMapping.APPLY_FAILED).build();
    }

    /**
     * 获取暴露的秒杀地址
     */
    @PostMapping("/getSeckillUrl")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public R<String> getSeckillUrl(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var applied = seckillApplicationFormService.contains(customerId);
        // 检查是否申请
        if (!applied) {
            return R.error()
                    .msg(MsgMapping.DOES_NOT_APPLY)
                    .build();
        }

        var between = Duration.between(LocalDateTime.now(), seckillActivityService.getLatest().getStartTime());

        // 检查秒杀是否开始
        if (between.toMillis() > 0) {
            return R.error()
                    .msg(MsgMapping.SECKILL_NOT_STARTED)
                    .build();
        }

        var seckillId = seckillActivityService.getLatestSeckillId();
        log.info("用户id {} 获取秒杀链接 {}", customerId, seckillId);

        return R.ok()
                .data(seckillId.toString());
    }

    /**
     * 初始化在Redis中秒杀活动的基本信息
     */
    @PostConstruct
    public void initActivityInfoInRedis() {
        // 从服务中获取秒杀活动信息
        var seckillId = seckillActivityService.getLatestSeckillId();
        var storage = seckillActivityService.getLatest().getAmount();
        var productPrice = seckillActivityService.getLatest().getProduct().getPrice();

        // 初始化Redis库存
        redisTemplate.opsForValue().set(Const.REDIS_SECKILL_ID_KEY, String.valueOf(seckillId));
        redisTemplate.opsForValue().set(Const.REDIS_STORAGE_PREFIX + seckillId, String.valueOf(storage));
        redisTemplate.opsForValue().set(Const.REDIS_PRODUCT_PRICE_PREFIX + seckillId, String.valueOf(productPrice));

        // 打印日志
        log.info("初始化Redis: 秒杀id -> {}", seckillId);
        log.info("初始化Redis: 库存 -> {}", storage);
        log.info("初始化Redis: 商品金额 -> {}", productPrice);
    }

    /**
     * 秒杀接口
     */
    @PostMapping("/seckill/{seckillUrl}")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public R<Void> seckill(@CookieValue(value = "token", required = false) String token,
                           @PathVariable String seckillUrl)
            throws JsonProcessingException {
        // region 通过标记检查是否卖完
        // 如果已经卖完就直接返回
        if (this.soldOut) {
            // 返回商品售完信息
            return R.error()
                    .msg(MsgMapping.PRODUCT_SOLD_OUT)
                    .build();
        }
        // endregion

        // region 检查信息安全
        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var applied = seckillApplicationFormService.contains(customerId);
        // 检查是否申请
        if (!applied) {
            return R.error()
                    .msg(MsgMapping.DOES_NOT_APPLY)
                    .build();
        }

        // 检查秒杀链接格式
        if (!Strs.isNum(seckillUrl)) {
            return R.error()
                    .msg(MsgMapping.INVALID_SECKILL_URL)
                    .build();
        }

        var seckillId = redisTemplate.opsForValue().get(Const.REDIS_SECKILL_ID_KEY);

        if (seckillId == null) {
            throw new RuntimeException("未从Redis中查询到秒杀id信息！");
        }

        // 检查秒杀链接
        if (!seckillId.equals(seckillUrl)) {
            return R.error()
                    .msg(MsgMapping.INVALID_SECKILL_URL)
                    .build();
        }
        // endregion

        // region 从redis检查重复购买
        // 从redis中检查重复购买
        var seckillSuccess = redisTemplate.opsForValue()
                .get(Const.REDIS_SECKILL_SUCCESS_PREFIX + seckillUrl + ":" + customerId);
        if (seckillSuccess != null) {
            return R.error()
                    .msg(MsgMapping.PURCHASE_REPEAT)
                    .build();
        }
        // endregion

        // region 扣库存，若卖完就加上标记；秒杀成功用户加redis
        // 直接扣库存并获取扣完的库存
        var storage = redisTemplate.opsForValue().decrement(Const.REDIS_STORAGE_PREFIX + seckillId);

        // 检查库存是否存在
        if (storage == null) {
            throw new RuntimeException("未从Redis中查询到库存信息！");
        }

        // 检查库存是否小于0
        if (storage < 0) {
            // 把库存加回去
            redisTemplate.opsForValue().increment(Const.REDIS_STORAGE_PREFIX + seckillId);

            // 标记商品已经售完
            this.soldOut = true;

            // 返回商品售完信息
            return R.error()
                    .msg(MsgMapping.PRODUCT_SOLD_OUT)
                    .build();
        }

        // 把秒杀成功用户放进redis (seckillSuccess-seckillId:customerId -> 1)
        redisTemplate.opsForValue()
                .set(Const.REDIS_SECKILL_SUCCESS_PREFIX + seckillId + ":" + customerId, "1");
        // endregion

        // region 异步下订单，提升用户体验
        // 获取订单金额
        var orderPrice = redisTemplate.opsForValue().get(Const.REDIS_PRODUCT_PRICE_PREFIX + seckillId);
        // 异步下订单
        paymentClient.placeOrder(new BasicOrder() {{
            setSeckillId(Integer.valueOf(seckillId));
            setAccountId(customerId);
            setMoney(Double.valueOf(Objects.requireNonNull(orderPrice)));
        }}).get();
        // endregion

        // 返回秒杀成功信息
        return R.ok()
                .msg(MsgMapping.SECKILL_SUCCESS)
                .build();
    }

    /**
     * 测试秒杀接口
     */
    @PostMapping("/testSeckill")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public R<Void> testSeckill(String token, String seckillUrl) {
        // 如果已经卖完就直接返回
        if (this.soldOut) {
            // 返回商品售完信息
            return R.error()
                    .msg(MsgMapping.PRODUCT_SOLD_OUT)
                    .build();
        }

        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var applied = seckillApplicationFormService.contains(customerId);
        // 检查是否申请
        if (!applied) {
            return R.error()
                    .msg(MsgMapping.DOES_NOT_APPLY)
                    .build();
        }

        // 检查秒杀链接格式
        if (!Strs.isNum(seckillUrl)) {
            return R.error()
                    .msg(MsgMapping.INVALID_SECKILL_URL)
                    .build();
        }

        var seckillId = redisTemplate.opsForValue().get(Const.REDIS_SECKILL_ID_KEY);

        if (seckillId == null) {
            throw new RuntimeException("未从Redis中查询到秒杀id信息！");
        }

        // 检查秒杀链接
        if (!seckillId.equals(seckillUrl)) {
            return R.error()
                    .msg(MsgMapping.INVALID_SECKILL_URL)
                    .build();
        }

        // 直接扣库存并获取扣完的库存
        var storage = redisTemplate.opsForValue().decrement(Const.REDIS_STORAGE_PREFIX + seckillId);

        // 检查库存是否存在
        if (storage == null) {
            throw new RuntimeException("未从Redis中查询到库存信息！");
        }

        // 检查库存是否小于0
        if (storage < 0) {
            // 把库存加回去
            redisTemplate.opsForValue().increment(Const.REDIS_STORAGE_PREFIX + seckillId);

            // 标记商品已经售完
            this.soldOut = true;

            // 返回商品售完信息
            return R.error()
                    .msg(MsgMapping.PRODUCT_SOLD_OUT)
                    .build();
        }

        // 把秒杀成功用户放进redis (seckillSuccess-seckillId:customerId -> 1)
        var randCustomerId = Rand.nextInt();
        redisTemplate.opsForValue()
                .set(Const.REDIS_SECKILL_SUCCESS_PREFIX + seckillId + ":" + randCustomerId, Const.REDIS_NULL_STR);

        // 获取订单金额
        var orderPrice = redisTemplate.opsForValue().get(Const.REDIS_PRODUCT_PRICE_PREFIX + seckillId);
        // 异步下订单
        paymentClient.placeOrder(new BasicOrder() {{
            setSeckillId(Integer.valueOf(seckillId));
            setAccountId(customerId);
            setMoney(Double.valueOf(Objects.requireNonNull(orderPrice)));
        }}).get();

        // 返回秒杀成功信息
        return R.ok()
                .msg(MsgMapping.SECKILL_SUCCESS)
                .build();
    }


    /**
     * 获取订单
     */
    @PostMapping("/getOrder")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public R<BasicOrder> getOrder(@CookieValue(value = "token", required = false) String token) {
        // 从token获取用户id
        Integer accountId;
        if ((accountId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        // 从redis中检查用户是否已经购买
        var seckillId = redisTemplate.opsForValue().get(Const.REDIS_SECKILL_ID_KEY);
        var seckillSuccess = redisTemplate.opsForValue()
                .get(Const.REDIS_SECKILL_SUCCESS_PREFIX + seckillId + ":" + accountId);
        if (seckillSuccess == null) {
            return R.error()
                    .msg(MsgMapping.NOT_PURCHASE)
                    .build();
        }

        // 目标订单
        BasicOrder order;

        // 轮询获取订单信息
        while ((order = paymentClient.getOrder(accountId).get()) == null) {
            // 订单还没准备好，延时重新获取
            Threads.sleep(100);
        }

        // 把订单号放进redis（seckillSuccess-seckillId:accountId -> orderId）
        redisTemplate.opsForValue()
                .set(Const.REDIS_SECKILL_SUCCESS_PREFIX + seckillId + ":" + accountId, order.getOrderId());

        // 请求成功直接返回订单数据
        return R.ok()
                .data(order);
    }

    /**
     * 支付
     */
    @PostMapping("/pay")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public R<PaymentStatus> pay(@CookieValue(value = "token", required = false) String token, String orderId) {
        // 检查订单号是否为空
        if (Objs.nullOrEmpty(orderId)) {
            return R.error()
                    .msg(MsgMapping.EMPTY_ORDER_ID)
                    .build();
        }

        // 检查token
        Integer accountId;
        if ((accountId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var seckillId = redisTemplate.opsForValue().get(Const.REDIS_SECKILL_ID_KEY);
        var realOrderId = redisTemplate.opsForValue()
                .get(Const.REDIS_SECKILL_SUCCESS_PREFIX + seckillId + ":" + accountId);

        // 从redis中检查用户是否已经购买
        if (realOrderId == null) {
            return R.error()
                    .msg(MsgMapping.NOT_PURCHASE)
                    .build();
        }

        // 检测用户是否获取订单
        if (realOrderId.equals(Const.REDIS_NULL_STR)) {
            return R.error()
                    .msg(MsgMapping.ORDER_NOT_REQUIRED)
                    .build();
        }

        // 检查用户订单号正确性
        if (!realOrderId.equals(orderId)) {
            return R.error()
                    .msg(MsgMapping.WRONG_ORDER_ID)
                    .build();
        }

        // 请求支付
        paymentClient.requestForPay(orderId).get();

        // 支付状态信息
        PaymentStatus paymentStatus;

        // 轮询获取支付状态信息
        while ((paymentStatus = paymentClient.getPaymentStatus(orderId).get()) == null) {
            // 支付状态信息还没准备好，延时重新获取
            Threads.sleep(100);
        }

        // 请求成功直接返回支付状态信息
        return R.ok()
                .data(paymentStatus);
    }
}
