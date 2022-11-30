package com.lingyuango.seckill.payment.utils;

import com.lingyuango.seckill.payment.common.Const;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * @author ChaconneLuo
 */
public class CheckDateStamp {

    private CheckDateStamp() {

    }

    public static boolean CheckOverTime(LocalDateTime dateTime, int time) {
        var timeOne = dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
        var timeTwo = System.currentTimeMillis();
        return timeTwo - timeOne <= time;
    }

    public static LocalDateTime convert(String source) {
        return LocalDateTime.parse(source, DateTimeFormatter.ofPattern(Const.DATE_TIME_FORMAT));
    }
}
