package com.lingyuango.seckill.service.impl;

import com.jmc.lang.Objs;
import com.jmc.lang.Threads;
import com.jmc.lang.Tries;
import com.jmc.net.R;
import com.lingyuango.seckill.client.PaymentClient;
import com.lingyuango.seckill.common.Const;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
import com.lingyuango.seckill.service.PaymentService;
import com.lingyuango.seckill.service.SeckillActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final PaymentClient paymentClient;
    private final StringRedisTemplate redisTemplate;
    private final SeckillActivityService seckillActivityService;

    /**
     * 线程池
     */
    private final ExecutorService pool = Executors.newFixedThreadPool(100);

    @Override
    public BasicOrder getOrder(Integer account) throws Exception {
        // 如果用户没有秒杀成功就抛出异常
        if (!seckillActivityService.hasSeckillSuccess(account)) {
            throw new Exception(MsgMapping.NOT_PURCHASE);
        }

        // 目标订单数据类
        R<BasicOrder> orderData;

        // 轮询获取订单信息
        while ((orderData = paymentClient.getOrder(account)).failed()) {
            // 订单还没准备好，延时重新获取
            Threads.sleep(100);
        }

        // 订单信息
        var order = Tries.tryReturnsT(orderData::getData);

        // 把订单号放进redis
        redisTemplate.opsForValue()
                .set(Const.REDIS_ORDER_GROUP + account, order.getOrderId());

        return order;
    }

    @Override
    public boolean isInvalidOrderId(Integer account, String orderId) {
        // 判断传入订单号是否为空
        if (Objs.nullOrEmpty(orderId)) {
            return true;
        }

        var realOrderId = redisTemplate.opsForValue()
                .get(Const.REDIS_ORDER_GROUP + account);

        return !orderId.equals(realOrderId);
    }

    @Override
    public boolean hasAlreadyPaid(Integer account) {
        return redisTemplate.opsForValue().get(Const.REDIS_PURCHASED_GROUP + account) != null;
    }

    @Override
    public PaymentStatus pay(Integer account, String orderId) throws Exception {
        // 检查用户订单号正确性
        if (isInvalidOrderId(account, orderId)) {
            throw new Exception(MsgMapping.WRONG_ORDER_ID);
        }

        // 检测用户重复支付
        if (hasAlreadyPaid(account)) {
            throw new Exception(MsgMapping.PURCHASE_REPEAT);
        }

        // 请求支付
        paymentClient.requestForPay(orderId);

        // 目标支付状态信息数据类
        R<PaymentStatus> paymentStatusData;

        // 重试次数
        int retry = 50;

        // 轮询获取支付状态
        while ((paymentStatusData = paymentClient.getPaymentStatus(orderId)).failed()) {
            // 如果不是支付状态信息未准备好，而是其他错误，直接抛出异常信息
            if (!MsgMapping.PAYMENT_STATUS_NOT_READY.equals(paymentStatusData.getMessage())) {
                throw new Exception(paymentStatusData.getMessage());
            }

            Threads.sleep(100);
            // 支付超时判断
            if (--retry == 0) {
                throw new Exception(MsgMapping.PAYMENT_TIMEOUT);
            }
        }

        // 把支付完成信息放入redis
        redisTemplate.opsForValue().set(Const.REDIS_PURCHASED_GROUP + account, "");

        // 返回支付信息
        return paymentStatusData.getData();
    }

    @Override
    public void placeOrderAsync(String seckillUrl, Integer account) {
        var order = new BasicOrder() {{
            setSeckillId(Integer.valueOf(seckillUrl));
            setAccountId(account);
            setMoney(seckillActivityService.getOrderPriceFromRedis(seckillUrl));
        }};

        pool.submit(() -> {
            paymentClient.placeOrder(order);
            log.info("下订单成功：{}", order);
        });
    }
}
