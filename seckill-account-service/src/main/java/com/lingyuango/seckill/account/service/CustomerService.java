package com.lingyuango.seckill.account.service;

import com.lingyuango.seckill.account.pojo.Customer;

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
     * 检查账号/身份证号和密码是否在数据库中有匹配
     */
    boolean contains(Customer customer);

    Customer getByAccountId(Integer accountId);

    /**
     * 是否能申请秒杀活动
     */
    boolean canApply(Integer accountId);
}
