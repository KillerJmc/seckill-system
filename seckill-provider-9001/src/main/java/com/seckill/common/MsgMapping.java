package com.seckill.common;

/**
 * 前后端信息映射
 * @author Jmc, Luo
 */
public interface MsgMapping {
    /**
     * 账号或密码为空
     */
    String ACCOUNT_PWD_NULL_OR_EMPTY = "账号或密码为空";

    /**
     * 账号或密码错误
     */
    String ACCOUNT_OR_PWD_ERROR = "账号或密码错误";

    /**
     * 登录成功
     */
    String LOGIN_SUCCESS = "登录成功";

    /**
     * 账号，身份证或密码为空
     */
    String ACCOUNT_ID_NUM_PWD_NULL_OR_EMPTY = "账号，身份证或密码为空";

    /**
     * 身份证号重复
     */
    String ID_NUM_REPEATED = "身份证号重复";

    /**
     * 注册成功
     */
    String REG_SUCCESS = "注册成功";

    /**
     * 身份证号格式错误
     */
    String ID_NUM_FORMAT_ERROR = "身份证号格式错误";
}
