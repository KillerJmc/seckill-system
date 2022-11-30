package com.lingyuango.seckill.account.service;

import com.lingyuango.seckill.account.pojo.Customer;

/**
 * @author Jmc
 */
public interface CustomerService {
    void insert(Customer customer) throws Exception;

    /**
     * 检查账号/身份证号和密码是否在数据库中有匹配
     */
    boolean contains(Customer customer);

    Customer getByAccount(Integer account);

    /**
     * 是否能申请秒杀活动
     */
    boolean canApply(Integer account);

    /**
     * 检查客户的身份证是否合法
     * @param idNumber 身份证号
     */
    void checkIdNum(String idNumber) throws Exception;
}
