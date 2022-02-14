package com.seckill.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * 计算器
 * @author Jmc
 */
public class Calculator {
    /**
     * 获取身份证号对应人的年龄（实岁）
     */
    public static int getAge(String idNumber) {
        // 校验身份证正确性
        if (!Verify.validIdNum(idNumber)) {
            throw new RuntimeException("Invalid id number: " + idNumber);
        }

        // ______20010809____ 身份证中出生日期开始下标为6，长度是8
        int startIdx = 6, endIdx = startIdx + 8;
        var birthday = LocalDate.parse(idNumber.substring(startIdx, endIdx), DateTimeFormatter.BASIC_ISO_DATE);

        var period = Period.between(birthday, LocalDate.now());
        return period.getYears();
    }
}
