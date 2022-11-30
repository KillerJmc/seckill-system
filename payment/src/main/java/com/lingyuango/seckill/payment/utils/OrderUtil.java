package com.lingyuango.seckill.payment.utils;

import cn.hutool.core.lang.Snowflake;

/**
 * @author ChaconneLuo
 */

public class OrderUtil {
    private OrderUtil() {

    }

    private static Snowflake instance;

    private static synchronized Snowflake getInstance() {
        if (instance == null) {
            instance = new Snowflake(1, 1);
        }
        return instance;
    }

    public static String getOrderId() {
        return getInstance().nextIdStr();
    }
}
