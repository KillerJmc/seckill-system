package com.lingyuango.seckill.account.service;

import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.pojo.CustomerInfo;

/**
 * @author Jmc
 */
public interface CustomerInfoService {
    CustomerInfo getByAccount(Integer account);

    /**
     * 通过用户基础信息和模拟用户信用信息构建初筛信息并插入数据库
     */
    void insert(Customer customer);
}
