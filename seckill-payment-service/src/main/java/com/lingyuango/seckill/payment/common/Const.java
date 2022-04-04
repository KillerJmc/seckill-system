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

}
