package com.lingyuango.seckill.common;

/**
 * 项目常量
 * @author Jmc
 */
public interface Const {
    /**
     * 允许跨域的地址
     */
    String CROSS_ORIGIN = "*";

    /**
     * 客户账号id相对于自然id的偏移值
     */
    int ACCOUNT_ID_OFFSET = 10000;

    /**
     * redis中token的组名
     */
    String REDIS_TOKEN_GROUP = "token:";
}
