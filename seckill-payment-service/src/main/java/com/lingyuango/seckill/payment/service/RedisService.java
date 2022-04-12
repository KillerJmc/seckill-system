package com.lingyuango.seckill.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import com.lingyuango.seckill.payment.pojo.Product;

public interface RedisService {

    void putPaymentStatus(PaymentStatus paymentStatus) throws JsonProcessingException;

    void putBasicOrder(BasicOrder basicOrder) throws JsonProcessingException;

    PaymentStatus getPaymentStatus(String orderId) throws JsonProcessingException;

    BasicOrder getBasicOrder(Integer accountId) throws JsonProcessingException;

    void putMessage(String orderId, String message);

    void deleteOrder(Integer accountId);

    Product getActivityProduct(Integer activityId) throws JsonProcessingException;

    void putActivityPrice(Integer activityId, Product product) throws JsonProcessingException;

}
