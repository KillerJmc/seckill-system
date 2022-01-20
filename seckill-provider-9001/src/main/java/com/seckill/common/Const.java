package com.seckill.common;

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
     * 管理员token前缀
     */
    String ADMIN_TOKEN_PREFIX = "admin-";

    /**
     * 客户token前缀
     */
    String CUSTOMER_TOKEN_PREFIX = "customer-";
}
