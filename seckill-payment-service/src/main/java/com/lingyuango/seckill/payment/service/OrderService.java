package com.lingyuango.seckill.payment.service;

import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.Order;

public interface OrderService {
    Order insert(BasicOrder basicOrder);
    Boolean update(String orderId);
}
