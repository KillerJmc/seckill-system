package com.lingyuango.seckill.account.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Token服务
 * @author Jmc
 */
public interface TokenService {
    /**
     * 添加登录的cookie
     * @param account 账号id
     * @param req     请求体对象
     * @param resp    响应体对象
     */
    void addLoginCookies(Integer account, HttpServletRequest req, HttpServletResponse resp);

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
     * 删除登录的cookie
     * @param token 登录凭证
     * @param req 请求体对象
     * @param resp 响应体对象
     */
    void deleteLoginCookies(String token, HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
