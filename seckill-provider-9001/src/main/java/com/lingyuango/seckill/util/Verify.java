package com.lingyuango.seckill.util;

import com.jmc.lang.Strs;

public class Verify {
    /**
     * 检查身份证号是否合法
     */
    public static boolean validIdNum(String idNum) {
        return idNum != null &&
                idNum.length() == 18 &&
                (Strs.isNum(idNum) || (Strs.isNum(idNum.substring(0, 17)) && Character.isLetter(idNum.charAt(17))));
    }
}
