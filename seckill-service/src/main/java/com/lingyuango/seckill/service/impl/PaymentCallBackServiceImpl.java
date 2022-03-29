package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.common.Const;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
import com.lingyuango.seckill.service.PaymentCallBackService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentCallBackServiceImpl implements PaymentCallBackService {
    private final StringRedisTemplate redisTemplate;

    @Override
    public void putOrderToRedis(BasicOrder basicOrder) {
        redisTemplate.opsForValue().set(
                Const.REDIS_ORDER_GROUP + basicOrder.getAccountId(),
                basicOrder.toString()
        );
    }

    @Override
    public void putPaymentStatusToRedis(PaymentStatus paymentStatus) {
        redisTemplate.opsForValue().set(
                Const.REDIS_PAYMENT_STATUS_GROUP + paymentStatus.getOrderId(),
                paymentStatus.toString()
        );
    }

    @Override
    public BasicOrder getOrder(Integer seckillId, Integer accountId) {
        return null;
    }

    @Override
    public PaymentStatus getPaymentStatus(String orderId) {
        return null;
    }
}
