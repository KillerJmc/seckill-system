package com.lingyuango.seckill.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;

public interface RedisService {

    void putPaymentStatus(PaymentStatus paymentStatus) throws JsonProcessingException;

    void putBasicOrder(BasicOrder basicOrder) throws JsonProcessingException;

    PaymentStatus getPaymentStatus(String orderId);

    BasicOrder getBasicOrder(Integer accountId);

}