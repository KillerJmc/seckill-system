package com.lingyuango.seckill.payment.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lingyuango.seckill.payment.common.Const;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import com.lingyuango.seckill.payment.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
/**
 * @author ChaconneLuo
 */
@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void putPaymentStatus(PaymentStatus paymentStatus) throws JsonProcessingException {
        redisTemplate.opsForValue().set(Const.REDIS_PAY_PREFIX + paymentStatus.getOrderId(), objectMapper.writeValueAsString(paymentStatus));
    }

    @Override
    public void putBasicOrder(BasicOrder basicOrder) throws JsonProcessingException {
        redisTemplate.opsForValue().set(Const.REDIS_ORDER_PREFIX + basicOrder.getAccountId(), objectMapper.writeValueAsString(basicOrder));
    }

    @Override
    public PaymentStatus getPaymentStatus(String orderId) throws JsonProcessingException {
        String result = redisTemplate.opsForValue().get(Const.REDIS_PAY_PREFIX + orderId);
        if (result == null) {
            return null;
        } else return objectMapper.readValue(result, PaymentStatus.class);
    }

    @Override
    public BasicOrder getBasicOrder(Integer accountId) throws JsonProcessingException {
        String result = redisTemplate.opsForValue().get(Const.REDIS_ORDER_PREFIX + accountId);
        if (result == null) {
            return null;
        } else return objectMapper.readValue(result, BasicOrder.class);
    }

}
