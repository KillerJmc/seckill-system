package com.lingyuango.seckill.payment.service.impl;

import com.lingyuango.seckill.payment.common.Const;
import com.lingyuango.seckill.payment.dao.OrderDao;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.Order;
import com.lingyuango.seckill.payment.service.OrderService;
import com.lingyuango.seckill.payment.utils.CheckDateStamp;
import com.lingyuango.seckill.payment.utils.OrderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author ChaconneLuo
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderDao orderDao;

    @Override
    public synchronized Order insert(BasicOrder basicOrder) {
        var seckillId = basicOrder.getSeckillId();
        var accountId = basicOrder.getAccountId();
        var date = LocalDateTime.now();
        var orderId = OrderUtil.getOrderId();
        var seckillOrder = new Order();
        seckillOrder.setSeckillId(seckillId);
        seckillOrder.setAccountId(accountId);
        seckillOrder.setGmtModified(date);
        seckillOrder.setGmtCreate(date);
        seckillOrder.setOrderId(orderId);
        seckillOrder.setPaid(false);
        orderDao.save(seckillOrder);
        return seckillOrder;
    }

    public boolean OrderOverTimeCheck(String orderId) {
        var order = orderDao.getOneByOrderId(orderId);
        return CheckDateStamp.CheckOverTime(order.getGmtCreate(), Const.ORDER_OVERTIME_MILLIONS);
    }

    @Override
    @Transactional
    public synchronized void update(String orderId) {
        var order = orderDao.getOneByOrderId(orderId);
        order.setPaid(true);
        orderDao.save(order);
    }

}
