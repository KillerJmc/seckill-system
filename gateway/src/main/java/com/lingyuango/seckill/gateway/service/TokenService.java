package com.lingyuango.seckill.gateway.service;

/**
 * Token服务
 * @author Jmc
 */
public interface TokenService {
    /**
     * 通过token获取客户账户id
     * @return 客户账户id
     */
    Integer getAccount(String token);
}
