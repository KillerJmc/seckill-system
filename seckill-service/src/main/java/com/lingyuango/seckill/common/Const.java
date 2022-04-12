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

    /**
     * redis中秒杀成功记录的前缀
     */
    String REDIS_SECKILL_SUCCESS_PREFIX = "seckillSuccess-";

    /**
     * redis中秒杀id的键名
     */
    String REDIS_SECKILL_ID_KEY = "seckillUrl";

    /**
     * redis中占位字符串
     */
    String REDIS_NULL_STR = "1";

    /**
     * redis中商品金额的前缀
     */
    String REDIS_PRODUCT_PRICE_PREFIX = "productPrice-";
}
