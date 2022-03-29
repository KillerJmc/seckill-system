package com.lingyuango.seckill.common;

/**
 * 项目常量
 * @author Jmc
 */
public interface Const {
    /**
     * redis中token的组名
     */
    String REDIS_TOKEN_GROUP = "token:";

    /**
     * token的cookie名称
     */
    String TOKEN_COOKIE_NAME = "token";

    /**
     * redis中订单的组名
     */
    String REDIS_ORDER_GROUP = "order:";

    /**
     * redis中支付状态的组名
     */
    String REDIS_PAYMENT_STATUS_GROUP = "paymentStatus:";

    /**
     * redis中库存的前缀
     */
    String REDIS_STORAGE_PREFIX = "storage-";
}