package com.lingyuango.seckill.common;

/**
 * 项目常量
 * @author Jmc
 */
public interface Const {
    /**
     * token的cookie名称
     */
    String TOKEN_COOKIE_NAME = "token";

    /**
     * redis中库存的键名
     */
    String REDIS_STORAGE_KEY = "storage";

    /**
     * redis中秒杀id的键名
     */
    String REDIS_SECKILL_ID_KEY = "seckillUrl";

    /**
     * redis中商品金额的键名
     */
    String REDIS_PRODUCT_PRICE_KEY = "productPrice";

    /**
     * redis中token的组名 <br>
     * token:<b>tokenStr</b> -> <b>accountId</b>
     */
    String REDIS_TOKEN_GROUP = "token:";

    /**
     * redis中秒杀成功记录的组名 <br>
     * seckillSuccess:<b>accountId</b> -> <b>""</b>
     */
    String REDIS_SECKILL_SUCCESS_GROUP = "seckillSuccess:";

    /**
     * redis中订单的组名 <br>
     * order:<b>accountId</b> -> <b>orderId</b>
     */
    String REDIS_ORDER_GROUP = "order:";

    /**
     * redis中表示已支付的组名 <br>
     * purchased:<b>accountId</b> -> <b>""</b>
     */
    String REDIS_PURCHASED_GROUP = "purchased:";
}
