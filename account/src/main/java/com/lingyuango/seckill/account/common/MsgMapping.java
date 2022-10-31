package com.lingyuango.seckill.account.common;

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
     * 姓名，身份证或密码为空
     */
    String NAME_ID_NUM_PWD_NULL_OR_EMPTY = "姓名，身份证或密码为空";

    /**
     * 身份证号重复
     */
    String ID_NUM_REPEATED = "身份证号重复";

    /**
     * 身份证号格式错误
     */
    String ID_NUM_FORMAT_ERROR = "身份证号格式错误";

    /**
     * 重复登录
     */
    String LOGIN_REPEAT = "重复登录";

    /**
     * 未登录
     */
    String NOT_LOGGED_ON = "您还未登录";

    /**
     * 用户初筛信息生成失败
     */
    String USER_PRE_SCREEN_INFO_CREATE_ERROR = "用户初筛信息生成失败";
}
