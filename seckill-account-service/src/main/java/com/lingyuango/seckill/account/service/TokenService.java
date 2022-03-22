package com.lingyuango.seckill.account.service;

/**
 * Token服务
 * @author Jmc
 */
public interface TokenService {
    /**
     * 创建token
     * @param accountId 账号id
     * @return token
     */
    String create(Integer accountId);

    /**
     * 将token -> 账户id存入redis
     */
    void putAccountId(String token, Integer accountId);

    /**
     * 通过token获取客户账户id
     * @return 客户账户id
     */
    Integer getAccountId(String token);
}
