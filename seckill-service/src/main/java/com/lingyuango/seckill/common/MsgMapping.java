package com.lingyuango.seckill.common;

/**
 * 前后端信息映射
 * @author Jmc, Luo
 */
public interface MsgMapping {
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
     * 商品已经售完
     */
    String PRODUCT_SOLD_OUT = "商品已经售完";

    /**
     * 重复购买
     */
    String PURCHASE_REPEAT = "重复购买";

    /**
     * 秒杀链接错误
     */
    String INVALID_SECKILL_URL = "秒杀链接错误";

    /**
     * 秒杀成功
     */
    String SECKILL_SUCCESS = "秒杀成功";

    /**
     * 还没有购买秒杀商品
     */
    String NOT_PURCHASE = "还没有购买秒杀商品";

    /**
     * 订单号错误
     */
    String WRONG_ORDER_ID = "订单号错误";

    /**
     * 没有规则信息
     */
    String NO_RULES = "没有规则信息";

    /**
     * 支付状态信息未准备好
     */
    String PAYMENT_STATUS_NOT_READY = "PAYMENT_STATUS_NOT_READY";
}
