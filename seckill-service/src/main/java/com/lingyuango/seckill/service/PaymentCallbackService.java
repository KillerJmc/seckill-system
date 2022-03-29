package com.lingyuango.seckill.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;

public interface PaymentCallbackService {
    /**
     * 把订单放进Redis <br>
     * order:accountId -> BasicOrder
     */
    void putOrderToRedis(BasicOrder basicOrder) throws JsonProcessingException;

    /**
     * 把订单支付状态放进Redis <br>
     * payment:accountId -> PaymentStatus
     */
    void putPaymentStatusToRedis(PaymentStatus paymentStatus) throws JsonProcessingException;

    /**
     * 从Redis中获取订单信息 <br>
     * 默认一个账号只对应一次活动，一个秒杀id
     */
    BasicOrder getOrder(Integer accountId) throws JsonProcessingException;

    /**
     * 从Redis获取订单支付状态
     */
    PaymentStatus getPaymentStatus(String orderId) throws JsonProcessingException;
}
