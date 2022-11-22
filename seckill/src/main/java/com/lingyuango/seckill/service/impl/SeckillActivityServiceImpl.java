package com.lingyuango.seckill.service.impl;

import com.jmc.lang.Strs;
import com.lingyuango.seckill.common.Const;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.dao.SeckillActivityDao;
import com.lingyuango.seckill.pojo.SeckillActivity;
import com.lingyuango.seckill.pojo.SeckillActivityRule;
import com.lingyuango.seckill.service.PaymentService;
import com.lingyuango.seckill.service.ProductService;
import com.lingyuango.seckill.service.SeckillActivityRuleService;
import com.lingyuango.seckill.service.SeckillActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
@Slf4j
public class SeckillActivityServiceImpl implements SeckillActivityService {
    private final SeckillActivityDao seckillActivityDao;
    private final SeckillActivityRuleService seckillActivityRuleService;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final StringRedisTemplate redisTemplate;

    /**
     * 商品是否售完（默认为false）
     */
    private volatile boolean soldOut = false;

    @Override
    public synchronized SeckillActivity getLatest() {
        var res = seckillActivityDao.getLatest();

        if (res == null) {
            return null;
        }

        var activityRule = seckillActivityRuleService.getById(res.getActivityRuleId());
        activityRule.setId(null);
        activityRule.setRuleId(null);
        res.setActivityRuleId(null);
        res.setRule(activityRule);

        var product = productService.getByProductId(res.getProductId());
        product.setId(null);
        product.setProductId(null);
        res.setProductId(null);
        res.setProduct(product);

        return res;
    }

    @Override
    public Long getCountDown() {
        var activity = getLatest();
        long countDown = Duration.between(LocalDateTime.now(), activity.getStartTime()).toSeconds();
        countDown = countDown < 0 ? 0 : countDown;
        return countDown;
    }

    @Override
    public boolean isSeckillNotStarted() {
        var between = Duration.between(LocalDateTime.now(), getLatest().getStartTime());
        return between.toMillis() > 0;
    }

    @Override
    public SeckillActivityRule getRule() throws Exception {
        var activity = seckillActivityDao.getLatest();
        if (activity == null) {
            throw new Exception(MsgMapping.NO_ACTIVITY);
        }

        var res = seckillActivityRuleService.getById(activity.getActivityRuleId());
        if (res == null) {
            throw new Exception(MsgMapping.NO_RULES);
        }

        res.setId(null);
        res.setRuleId(null);
        return res;
    }

    @Override
    public Integer getLatestSeckillId() {
        return seckillActivityDao.getLatestSeckillId();
    }

    @Override
    public void initRedis() {
        // 从服务中获取秒杀活动信息
        var seckillUrl = getLatestSeckillId();
        var storage = getLatest().getAmount();
        var productPrice = getLatest().getProduct().getPrice();

        // 初始化Redis库存
        redisTemplate.opsForValue().set(Const.REDIS_SECKILL_URL_KEY, String.valueOf(seckillUrl));
        redisTemplate.opsForValue().set(Const.REDIS_STORAGE_KEY, String.valueOf(storage));
        redisTemplate.opsForValue().set(Const.REDIS_PRODUCT_PRICE_KEY, String.valueOf(productPrice));

        // 打印日志
        log.info("初始化Redis: 秒杀url -> {}", seckillUrl);
        log.info("初始化Redis: 库存 -> {}", storage);
        log.info("初始化Redis: 商品金额 -> {}", productPrice);
    }

    @Override
    public String getSeckillUrlFromRedis() {
        return redisTemplate.opsForValue().get(Const.REDIS_SECKILL_URL_KEY);
    }

    @Override
    public Double getOrderPriceFromRedis(String seckillUrl) {
        return Double.valueOf(Objects.requireNonNull(
                redisTemplate.opsForValue().get(Const.REDIS_PRODUCT_PRICE_KEY)));
    }

    @Override
    public boolean isInvalidSeckillUrl(String seckillUrl) {
        // 秒杀链接必须全为数字
        if (!Strs.isNum(seckillUrl)) {
            return true;
        }

        // 从redis中查真实秒杀链接
        var seckillId = getSeckillUrlFromRedis();

        if (seckillId == null) {
            throw new RuntimeException("未从Redis中查询到秒杀id信息！");
        }

        // 两者必须相同
        return !seckillId.equals(seckillUrl);
    }

    @Override
    public boolean hasSeckillSuccess(Integer account) {
        return redisTemplate.opsForValue()
                .get(Const.REDIS_SECKILL_SUCCESS_GROUP + account) != null;
    }

    @Override
    public void seckill(String seckillUrl, Integer account) throws Exception {
        // 检查秒杀链接是否合法
        if (isInvalidSeckillUrl(seckillUrl)) {
            throw new Exception(MsgMapping.INVALID_SECKILL_URL);
        }

        // 检查非法访问
        if (isSeckillNotStarted()) {
            throw new Exception(MsgMapping.INVALID_ACCESS);
        }

        // 检查重复购买
        if (hasSeckillSuccess(account)) {
            throw new Exception(MsgMapping.PURCHASE_REPEAT);
        }

        // 检查是否售完
        if (this.soldOut) {
            throw new Exception(MsgMapping.PRODUCT_SOLD_OUT);
        }

        // 扣库存
        decreaseStorage(seckillUrl, account);

        // 异步下订单
        paymentService.placeOrderAsync(seckillUrl, account);
    }

    @Override
    public void decreaseStorage(String seckillId, Integer account) throws Exception {
        // 直接扣库存并获取扣完的库存
        var storage = redisTemplate.opsForValue().decrement(Const.REDIS_STORAGE_KEY);

        // 检查库存是否存在
        if (storage == null) {
            throw new RuntimeException("未从Redis中查询到库存信息！");
        }

        // 检查库存是否小于0
        if (storage < 0) {
            // 把库存加回去
            redisTemplate.opsForValue().increment(Const.REDIS_STORAGE_KEY);

            // 标记商品已经售完
            this.soldOut = true;

            throw new Exception(MsgMapping.PRODUCT_SOLD_OUT);
        }

        // 把秒杀成功用户放进redis (seckillSuccess:account -> 1)
        redisTemplate.opsForValue()
                .set(Const.REDIS_SECKILL_SUCCESS_GROUP + account, "");
    }
}
