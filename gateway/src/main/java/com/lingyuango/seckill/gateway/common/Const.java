package com.lingyuango.seckill.gateway.common;

/**
 * 常量类
 * @author Jmc
 */
public interface Const {
    /**
     * cookie的token名称
     */
    String COOKIE_TOKEN_NAME = "token";


    /**
     * redis中token的组名
     */
    String REDIS_TOKEN_GROUP = "token:";

    /**
     * cookie的账号名称
     */
    String COOKIE_ACCOUNT_NAME = "account";
}
