package com.lingyuango.seckill.service.impl;

import com.jmc.lang.Objs;
import com.jmc.lang.Strs;
import com.jmc.lang.Threads;
import com.jmc.lang.ref.Pointer;
import com.jmc.net.R;
import com.lingyuango.seckill.client.PaymentClient;
import com.lingyuango.seckill.common.Const;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
import com.lingyuango.seckill.pojo.SeckillActivity;
import com.lingyuango.seckill.pojo.SeckillActivityRule;
import com.lingyuango.seckill.service.ProductService;
import com.lingyuango.seckill.service.SeckillActivityRuleService;
import com.lingyuango.seckill.service.SeckillActivityService;
import com.lingyuango.seckill.dao.SeckillActivityDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SeckillActivityServiceImpl implements SeckillActivityService {
    private final SeckillActivityDao seckillActivityDao;
    private final SeckillActivityRuleService seckillActivityRuleService;
    private final ProductService productService;
    private final PaymentClient paymentClient;
    private final StringRedisTemplate redisTemplate;

    /**
     * 线程池
     */
    private final ExecutorService pool = Executors.newFixedThreadPool(100);

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
    public SeckillActivityRule getRule() {
        var activity = seckillActivityDao.getLatest();
        if (activity == null) {
            return null;
        }

        var res = seckillActivityRuleService.getById(activity.getActivityRuleId());
        if (res == null) {
            return null;
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
    public void placeOrderAsync(BasicOrder order) {
        pool.submit(() -> {
            paymentClient.placeOrder(order);
            log.info("下订单成功：{}", order);
        });
    }

    @Override
    public void initRedis() {
        // 从服务中获取秒杀活动信息
        var seckillId = getLatestSeckillId();
        var storage = getLatest().getAmount();
        var productPrice = getLatest().getProduct().getPrice();

        // 初始化Redis库存
        redisTemplate.opsForValue().set(Const.REDIS_SECKILL_ID_KEY, String.valueOf(seckillId));
        redisTemplate.opsForValue().set(Const.REDIS_STORAGE_KEY, String.valueOf(storage));
        redisTemplate.opsForValue().set(Const.REDIS_PRODUCT_PRICE_KEY, String.valueOf(productPrice));

        // 打印日志
        log.info("初始化Redis: 秒杀id -> {}", seckillId);
        log.info("初始化Redis: 库存 -> {}", storage);
        log.info("初始化Redis: 商品金额 -> {}", productPrice);
    }

    @Override
    public String getSeckillIdFromRedis() {
        return redisTemplate.opsForValue().get(Const.REDIS_SECKILL_ID_KEY);
    }

    @Override
    public Double getOrderPriceFromRedis(String seckillUrl) {
        return Double.valueOf(Objects.requireNonNull(
                redisTemplate.opsForValue().get(Const.REDIS_PRODUCT_PRICE_KEY)));
    }

    @Override
    public boolean isInvalidOrderId(Integer accountId, String orderId) {
        // 判断传入订单号是否为空
        if (Objs.nullOrEmpty(orderId)) {
            return true;
        }

        var realOrderId = redisTemplate.opsForValue()
                .get(Const.REDIS_ORDER_GROUP + accountId);

        return !orderId.equals(realOrderId);
    }

    @Override
    public boolean isSoldOut() {
        return this.soldOut;
    }

    @Override
    public boolean isInvalidSeckillUrl(String seckillUrl) {
        // 秒杀链接必须全为数字
        if (!Strs.isNum(seckillUrl)) {
            return true;
        }

        // 从redis中查真实秒杀链接
        var seckillId = redisTemplate.opsForValue().get(Const.REDIS_SECKILL_ID_KEY);

        if (seckillId == null) {
            throw new RuntimeException("未从Redis中查询到秒杀id信息！");
        }

        // 两者必须相同
        return !seckillId.equals(seckillUrl);
    }

    @Override
    public boolean hasSeckillSuccess(String seckillId, Integer accountId) {
        return redisTemplate.opsForValue()
                .get(Const.REDIS_SECKILL_SUCCESS_GROUP + accountId) != null;
    }

    @Override
    public boolean hasAlreadyPaid(Integer accountId) {
        return redisTemplate.opsForValue().get(Const.REDIS_PURCHASED_GROUP + accountId) != null;
    }

    @Override
    public boolean decreaseStorage(String seckillId, Integer accountId) {
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

            // 返回扣库存失败
            return false;
        }

        // 把秒杀成功用户放进redis (seckillSuccess:accountId -> 1)
        redisTemplate.opsForValue()
                .set(Const.REDIS_SECKILL_SUCCESS_GROUP + accountId, "");
        return true;
    }

    @Override
    public BasicOrder getOrder(String seckillId, Integer accountId) {
        // 目标订单数据类
        R<BasicOrder> orderData;

        // 轮询获取订单信息
        while ((orderData = paymentClient.getOrder(accountId)).failed()) {
            // 订单还没准备好，延时重新获取
            Threads.sleep(100);
        }

        // 订单信息
        var order = orderData.get();

        // 把订单号放进redis
        redisTemplate.opsForValue()
                .set(Const.REDIS_ORDER_GROUP + accountId, order.getOrderId());

        return order;
    }

    @Override
    public PaymentStatus getPaymentStatus(Integer accountId, String orderId, Pointer<String> errorMsgPtr) {
        // 目标支付状态信息数据类
        R<PaymentStatus> paymentStatusData;

        while ((paymentStatusData = paymentClient.getPaymentStatus(orderId)).failed()) {
            // 如果不是因为支付状态信息未准备好
            if (!MsgMapping.PAYMENT_STATUS_NOT_READY.equals(paymentStatusData.getMessage())) {
                // 把错误信息放进指针中
                errorMsgPtr.reset(paymentStatusData.getMessage());
                return null;
            }
            Threads.sleep(100);
        }

        // 把支付完成信息放入redis
        redisTemplate.opsForValue().set(Const.REDIS_PURCHASED_GROUP + accountId, "");

        return paymentStatusData.get();
    }
}
