package com.seckill.service;

public interface TokenService {
    /**
     * 通过token获取管理员名称
     */
    String getAdminName(String token);

    /**
     * 根据token获取客户名称
     */
    String getCustomerName(String token);
}
