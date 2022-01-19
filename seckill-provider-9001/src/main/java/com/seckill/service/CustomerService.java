package com.seckill.service;

import com.seckill.pojo.Customer;

/**
 * @author Jmc
 */
public interface CustomerService {
    int insert(Customer customer);

    /**
     * 提供账号和密码的Customer对象
     */
    boolean contains(Customer customer);
}
