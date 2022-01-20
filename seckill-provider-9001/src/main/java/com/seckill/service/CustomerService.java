package com.seckill.service;

import com.seckill.pojo.Customer;

/**
 * @author Jmc
 */
public interface CustomerService {
    /**
     * 插入
     * @return 是否插入成功（只要身份证号重复就失败）
     */
    boolean insert(Customer customer);

    /**
     * 提供账号和密码的Customer对象
     */
    boolean contains(Customer customer);
}
