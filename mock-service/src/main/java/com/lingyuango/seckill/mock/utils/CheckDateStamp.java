package com.lingyuango.seckill.mock.utils;

import com.lingyuango.seckill.mock.common.Const;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
}
