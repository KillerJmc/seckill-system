package com.lingyuango.seckill.payment.utils;

import com.lingyuango.seckill.payment.common.Const;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author ChaconneLuo
 */
public class CheckDateStamp {

    private CheckDateStamp() {

    }

    public static boolean CheckOverTime(LocalDateTime dateTime) {
        var timeOne = dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        var timeTwo = System.currentTimeMillis();
        return timeTwo - timeOne <= Const.TEN_MILLIONS;
    }

    public static LocalDateTime convert(String source) {
        return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
