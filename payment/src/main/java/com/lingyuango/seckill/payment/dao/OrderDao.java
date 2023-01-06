package com.lingyuango.seckill.payment.dao;

import com.lingyuango.seckill.payment.pojo.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lingyuango
 */
@Repository
public interface OrderDao extends JpaRepository<Order, Long> {
    Order getOneByOrderId(String orderId);
}
