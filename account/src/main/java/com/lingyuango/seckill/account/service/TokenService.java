package com.lingyuango.seckill.account.service;

import javax.servlet.http.HttpServletResponse;

/**
 * Token服务
 * @author Jmc
 */
public interface TokenService {
    /**
     * 创建token并设置2个cookie：token和account
     * @param account 账号id
     * @param resp 响应体对象
     */
    void createAndSetCookies(Integer account, HttpServletResponse resp);

    /**
     * 将token -> 账户id存入redis
     */
    void putAccount(String token, Integer account);

    /**
     * 通过token获取客户账户id
     * @return 客户账户id
     */
    Integer getAccount(String token);

    /**
     * 删除token
     *
     * @param token 登录凭证
     */
    void delete(String token) throws Exception;
}
