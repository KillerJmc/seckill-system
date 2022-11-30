package com.lingyuango.seckill.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import com.lingyuango.seckill.payment.pojo.Product;

public interface RedisService {

    void putPaymentStatus(PaymentStatus paymentStatus) throws JsonProcessingException;

    void putBasicOrder(BasicOrder basicOrder) throws JsonProcessingException;

    R<PaymentStatus> getPaymentStatus(String orderId) throws JsonProcessingException;

    R<BasicOrder> getBasicOrder(Integer accountId) throws JsonProcessingException;

    void putMessage(String orderId, String message);

    Product getActivityProduct(Integer activityId) throws JsonProcessingException;

    void putActivityPrice(Integer activityId, Product product) throws JsonProcessingException;

}
