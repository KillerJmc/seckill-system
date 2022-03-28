package com.lingyuango.seckill.payment.service;

import com.lingyuango.seckill.payment.pojo.BasicOrder;

public interface OrderService {
    String insert(BasicOrder basicOrder);
    Boolean update(String orderId);
}
