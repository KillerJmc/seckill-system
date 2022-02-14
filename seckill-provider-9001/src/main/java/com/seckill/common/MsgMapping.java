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
     * 姓名，身份证或密码为空
     */
    String NAME_ID_NUM_PWD_NULL_OR_EMPTY = "姓名，身份证或密码为空";

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

    /**
     * 重复登录
     */
    String LOGIN_REPEAT = "重复登录";

    /**
     * 未登录
     */
    String NOT_LOGGED_ON = "您还未登录";

    /**
     * 活动申请成功
     */
    String APPLY_SUCCESS = "申请成功";

    /**
     * 活动申请失败
     */
    String APPLY_FAILED = "申请失败，您不符合申请条件";

    /**
     * 重复申请
     */
    String APPLY_REPEAT = "重复申请";

    /**
     * 没有申请活动
     */
    String DOES_NOT_APPLY = "没有申请活动";

    /**
     * 秒杀活动还未开始
     */
    String SECKILL_NOT_STARTED = "秒杀活动还未开始";

    /**
     * 秒杀活动已经结束
     */
    String SECKILL_ENDED = "秒杀活动已经结束";
}
