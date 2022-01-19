package com.seckill.service;

import com.seckill.pojo.Customer;

import java.util.List;

/**
 * @author Jmc
 */
public interface SeckillSuccessService {
    List<Customer> getList(Integer seckillId);
}
