package com.seckill.service;

/**
 * Token服务
 * @author Jmc
 */
public interface TokenService {
    /**
     * 将token -> 账户名存入redis
     */
    void putAccountName(String token, String accountName);

    /**
     * 通过token获取管理员或客户账户名称
     */
    String getAccountName(String token);
}
