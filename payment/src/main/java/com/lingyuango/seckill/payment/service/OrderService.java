package com.lingyuango.seckill.payment.service;

import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.Order;

public interface OrderService {
    boolean OrderOverTimeCheck(String orderId);
    Order insert(BasicOrder basicOrder);
    void update(String orderId);
}
