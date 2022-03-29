package com.lingyuango.seckill.service;

import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;

public interface PaymentCallBackService {
    /**
     * 把订单放进Redis <br>
     * order:accountId -> BasicOrder
     */
    void putOrderToRedis(BasicOrder basicOrder);

    /**
     * 把订单支付状态放进Redis <br>
     * payment:accountId -> PaymentStatus
     */
    void putPaymentStatusToRedis(PaymentStatus paymentStatus);

    /**
     * 从Redis中获取订单信息
     */
    BasicOrder getOrderFromRedis(Integer seckillId, Integer accountId);

    /**
     * 从Redis获取订单支付状态
     */
    PaymentStatus getPaymentStatusFromRedis(String orderId);
}
