package com.lingyuango.seckill.account.common;

/**
 * 项目常量
 * @author Jmc
 */
public interface Const {
    /**
     * 客户账号id相对于自然id的偏移值
     */
    int ACCOUNT_ID_OFFSET = 10000;

    /**
     * redis中token的组名
     */
    String REDIS_TOKEN_GROUP = "token:";

    /**
     * token的cookie名称
     */
    String COOKIE_TOKEN_NAME = "token";

    /**
     * 账号的cookie名称
     */
    String COOKIE_ACCOUNT_NAME = "account";
}
