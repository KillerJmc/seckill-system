package com.lingyuango.seckill.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmc.lang.Strs;
import com.jmc.lang.Threads;
import com.jmc.net.HttpStatus;
import com.jmc.net.R;
import com.jmc.util.Rand;
import com.lingyuango.seckill.client.PaymentClient;
import com.lingyuango.seckill.client.PreScreeningClient;
import com.lingyuango.seckill.client.SeckillSuccessClient;
import com.lingyuango.seckill.client.StorageClient;
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
    private final PaymentCallbackService paymentCallbackService;
    private final StorageClient storageClient;
    private final SeckillSuccessClient seckillSuccessClient;
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
     * @return
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
     * 初始化在Redis中秒杀产品的库存
     */
    @PostConstruct
    public void initStorageInRedis() {
        var seckillId = seckillActivityService.getLatestSeckillId();
//        var storage = storageClient.getStorage(seckillId).get();

        // 初始化Redis库存
        redisTemplate.opsForValue().set(Const.REDIS_SECKILL_ID_KEY, String.valueOf(seckillId));
        redisTemplate.opsForValue().set(Const.REDIS_STORAGE_PREFIX + seckillId, String.valueOf(100000));
        log.info("初始化Redis 秒杀id：{}", seckillId);
        log.info("初始化Redis 库存：{}", 100000);
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
                .set(Const.REDIS_SECKILL_SUCCESS_PREFIX + seckillId + ":" + randCustomerId, "1");

        // 异步下订单，提升用户体验
//        paymentClient.placeOrder(new BasicOrder() {{
//            setSeckillId(seckillId);
//            setAccountId(randCustomerId);
//        }}).get();

//        log.info("用户id：{}，秒杀成功", randCustomerId);

        // 返回秒杀成功信息
        return R.ok()
                .msg(MsgMapping.SECKILL_SUCCESS)
                .build();
    }

    /**
     * 秒杀接口
     */
    @PostMapping("/seckill/{seckillUrl}")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public R<Void> seckill(@CookieValue(value = "token", required = false) String token,
                                 @PathVariable String seckillUrl) throws JsonProcessingException {
        // 如果已经卖完就直接返回
        if (this.soldOut) {
            // 返回商品售完信息
            return R.error()
                    .msg(MsgMapping.PRODUCT_SOLD_OUT)
                    .build();
        }

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

        // 从redis中检查重复购买
        var seckillSuccess = redisTemplate.opsForValue()
                .get(Const.REDIS_SECKILL_SUCCESS_PREFIX + seckillUrl + ":" + customerId);
        if (seckillSuccess != null) {
            return R.error()
                    .msg(MsgMapping.PURCHASE_REPEAT)
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
        redisTemplate.opsForValue()
                .set(Const.REDIS_SECKILL_SUCCESS_PREFIX + seckillId + ":" + customerId, "1");

        // 异步下订单，提升用户体验
        paymentClient.placeOrder(new BasicOrder() {{
            setSeckillId(Integer.valueOf(seckillId));
            setAccountId(customerId);
        }}).get();

        log.info("用户id：{}，秒杀成功", customerId);

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

        // 轮询获取订单信息
        while (true) {
            // 发送获取订单请求
            var orderData = paymentClient.getOrder(accountId);

            // 如果请求错误
            if (orderData.getCode() == HttpStatus.ERROR) {
                // 如果不是发生订单还未准备好的错误（其他错误）
                if (!MsgMapping.ORDER_NOT_READY.equals(orderData.getMessage())) {
                    // 直接返回错误信息
                    return orderData;
                }
                // 否则就是订单未准备好，延时重新获取
                Threads.sleep(100);
                continue;
            }

            // 请求成功直接返回订单数据
            return orderData;
        }
    }


}
