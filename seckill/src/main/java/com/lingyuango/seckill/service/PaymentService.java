package com.lingyuango.seckill.service;

import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;

/**
 * @author Jmc
 */
public interface PaymentService {
    /**
     * 获取订单
     */
    BasicOrder getOrder(Integer account) throws Exception;

    /**
     * 判断订单号是否非法
     */
    boolean isInvalidOrderId(Integer account, String orderId);

    /**
     * 判断是否已经付款
     */
    boolean hasAlreadyPaid(Integer account);

    /**
     * 支付订单
     */
    PaymentStatus pay(Integer account, String orderId) throws Exception;

    /**
     * 利用线程池异步发送消息下单
     * @param seckillUrl 秒杀url
     * @param account 下订单的账号
     */
    void placeOrderAsync(String seckillUrl, Integer account);
}
