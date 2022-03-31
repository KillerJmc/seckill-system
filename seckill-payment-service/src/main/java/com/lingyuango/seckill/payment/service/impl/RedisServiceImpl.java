package com.lingyuango.seckill.payment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lingyuango.seckill.payment.common.Const;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import com.lingyuango.seckill.payment.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author ChaconneLuo
 */
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate redisTemplate;


    @Override
    public void putPaymentStatus(PaymentStatus paymentStatus) {
        redisTemplate.opsForValue().set(Const.REDIS_PAY_PREFIX + paymentStatus.getOrderId(), JSONObject.toJSONString(paymentStatus));
    }

    @Override
    public void putBasicOrder(BasicOrder basicOrder) {
        redisTemplate.opsForValue().set(Const.REDIS_PAY_PREFIX + basicOrder.getOrderId(), JSONObject.toJSONString(basicOrder));
    }

    @Override
    public PaymentStatus getPaymentStatus(String orderId) {
        String result = redisTemplate.opsForValue().get(Const.REDIS_PAY_PREFIX + orderId);
        if (result == null) {
            return null;
        } else return JSONObject.parseObject(result, PaymentStatus.class);
    }

    @Override
    public BasicOrder getBasicOrder(Integer accountId) {
        String result = redisTemplate.opsForValue().get(Const.REDIS_ORDER_PREFIX + accountId);
        if (result == null) {
            return null;
        } else return JSONObject.parseObject(result, BasicOrder.class);
    }
}
