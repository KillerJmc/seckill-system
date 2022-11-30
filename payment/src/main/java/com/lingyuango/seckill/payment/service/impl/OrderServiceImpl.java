package com.lingyuango.seckill.payment.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
        var seckillOrder = new Order() {{
            setSeckillId(seckillId);
            setAccountId(accountId);
            setGmtModified(date);
            setGmtCreate(date);
            setOrderId(orderId);
            setPaid(false);
        }};
        orderDao.insert(seckillOrder);
        return seckillOrder;
    }

    public boolean OrderOverTimeCheck(String orderId) {
        var order = orderDao.selectOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderId, orderId));
        return CheckDateStamp.CheckOverTime(order.getGmtCreate(), Const.ORDER_OVERTIME_MILLIONS);
    }

    @Override
    @Transactional
    public synchronized Boolean update(String orderId) {
        var order = orderDao.selectOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderId, orderId));
        order.setPaid(true);
        return orderDao.update(order, Wrappers.<Order>lambdaUpdate().eq(Order::getOrderId, orderId)) != 0;
    }

    public Integer getAccountIdFromOrder(String orderId) {
        var order = orderDao.selectOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderId, orderId));
        return order != null ? order.getAccountId() : null;
    }
}
