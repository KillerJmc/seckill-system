package com.lingyuango.seckill.payment.common;

/**
 * @author ChaconneLuo
 */

public interface Const {
    String Appid = "Appid0618f40eed12957375af2eea72925933";

    String secKey = "Secret_Keyc2b82f4483324035337255fb777122bb";

    int TEN_MILLIONS = 10 * 60 * 1000;

    String REDIS_ORDER_PREFIX = "ORDER:";

    String REDIS_PAY_PREFIX = "PAY-";

    String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    String ORDER_IN_BINDING = "placeOrder-out-0";

    String PAY_IN_BINDING = "requestForPay-out-0";

    int ORDER_OVERTIME_MILLIONS = 30 * 60 * 1000;

    String REDIS_PRICE_PREFIX = "PRICE-";

}
