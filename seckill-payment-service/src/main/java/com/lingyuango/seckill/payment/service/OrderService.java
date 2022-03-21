package com.lingyuango.seckill.payment.service;

import com.lingyuango.seckill.payment.pojo.OrderMessage;

public interface OrderService {
    String insert(OrderMessage orderMessage);
    Boolean update(String orderId);
}
