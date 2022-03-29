package com.lingyuango.seckill.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmc.lang.Threads;
import com.lingyuango.seckill.common.Const;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
import com.lingyuango.seckill.service.PaymentCallbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCallbackServiceImpl implements PaymentCallbackService {
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper JSON;

    @Override
    public void putOrderToRedis(BasicOrder basicOrder) throws JsonProcessingException {
        redisTemplate.opsForValue().set(
                Const.REDIS_ORDER_GROUP + basicOrder.getAccountId(),
                JSON.writeValueAsString(basicOrder)
        );
    }

    @Override
    public void putPaymentStatusToRedis(PaymentStatus paymentStatus) throws JsonProcessingException {
        redisTemplate.opsForValue().set(
                Const.REDIS_PAYMENT_STATUS_GROUP + paymentStatus.getOrderId(),
                JSON.writeValueAsString(paymentStatus)
        );
    }

    @Override
    public BasicOrder getOrder(Integer accountId) throws JsonProcessingException {
        var operation = redisTemplate.opsForValue();
        String key = Const.REDIS_ORDER_GROUP + accountId, value;

        while ((value = operation.get(key)) == null) {
            // 每10ms尝试获取一次订单信息
            Threads.sleep(10);
        }

        return JSON.readValue(value, BasicOrder.class);
    }

    @Override
    public PaymentStatus getPaymentStatus(String orderId) throws JsonProcessingException {
        var operation = redisTemplate.opsForValue();
        String key = Const.REDIS_PAYMENT_STATUS_GROUP + orderId, value;

        while ((value = operation.get(key)) == null) {
            // 每10ms尝试获取一次支付状态信息
            Threads.sleep(10);
        }

        return JSON.readValue(value, PaymentStatus.class);
    }
}
