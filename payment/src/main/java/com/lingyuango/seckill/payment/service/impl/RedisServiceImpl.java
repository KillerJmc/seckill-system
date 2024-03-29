package com.lingyuango.seckill.payment.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmc.net.R;
import com.lingyuango.seckill.payment.client.SeckillActivityClient;
import com.lingyuango.seckill.payment.common.Const;
import com.lingyuango.seckill.payment.common.MsgMapping;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import com.lingyuango.seckill.payment.pojo.Product;
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
    private final SeckillActivityClient seckillActivityClient;
    private final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void putPaymentStatus(PaymentStatus paymentStatus) throws JsonProcessingException {
        redisTemplate.opsForValue().set(Const.REDIS_PAY_PREFIX + paymentStatus.getOrderId(), objectMapper.writeValueAsString(paymentStatus));
    }

    @Override
    public void putBasicOrder(BasicOrder basicOrder) throws JsonProcessingException {
        redisTemplate.opsForValue().set(Const.REDIS_ORDER_PREFIX + basicOrder.getAccountId(), objectMapper.writeValueAsString(basicOrder), Const.ORDER_OVERTIME_MILLIONS, TimeUnit.MILLISECONDS);
    }

    @Override
    public R<PaymentStatus> getPaymentStatus(String orderId) throws JsonProcessingException {
        String result = redisTemplate.opsForValue().get(Const.REDIS_PAY_PREFIX + orderId);
        if (result != null) {
            if (!(result.startsWith("{") && result.endsWith("}"))) {
                return R.error(result);
            } else return R.ok(objectMapper.readValue(result, PaymentStatus.class));
        } else return R.error(MsgMapping.PAYMENT_STATUS_NOT_READY);
    }

    @Override
    public R<BasicOrder> getBasicOrder(Integer accountId) throws JsonProcessingException {
        String result = redisTemplate.opsForValue().get(Const.REDIS_ORDER_PREFIX + accountId);
        if (result != null) {
            if (!(result.startsWith("{") && result.endsWith("}"))) {
                return R.error(result);
            } else return R.ok(objectMapper.readValue(result, BasicOrder.class));
        } else return R.error(MsgMapping.ORDER_NOT_READY);
    }

    @Override
    public void putMessage(String orderId, String message) {
        redisTemplate.opsForValue().set(Const.REDIS_PAY_PREFIX + orderId, message);
    }

    @Override
    public Product getActivityProduct(Integer activityId) throws JsonProcessingException {
        String s = redisTemplate.opsForValue().get(Const.REDIS_PRICE_PREFIX + activityId);
        if (s != null) {
            return objectMapper.readValue(s, Product.class);
        } else {
            var product = seckillActivityClient.getProduct(activityId).getData();
            if (product != null) {
                putActivityPrice(activityId, product);
            }
            return product;
        }
    }

    @Override
    public void putActivityPrice(Integer activityId, Product product) throws JsonProcessingException {
        redisTemplate.opsForValue().set(Const.REDIS_PRICE_PREFIX + activityId, objectMapper.writeValueAsString(product));
    }

}
