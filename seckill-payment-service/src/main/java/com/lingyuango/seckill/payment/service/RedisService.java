package com.lingyuango.seckill.payment.service;

import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;

public interface RedisService {

    void putPaymentStatus(PaymentStatus paymentStatus);

    void putBasicOrder(BasicOrder basicOrder);

    PaymentStatus getPaymentStatus(String orderId);

    BasicOrder getBasicOrder(Integer accountId);

}
