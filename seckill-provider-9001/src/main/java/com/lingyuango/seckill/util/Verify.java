package com.lingyuango.seckill.util;

import com.jmc.lang.Strs;

import java.util.Collection;
import java.util.Map;

public class Verify {
    public static boolean nullOrEmpty(Object... objs) {
        if (objs == null) {
            return true;
        }

        for (Object o : objs) {
            if (o == null) {
                return true;
            } else if (o instanceof String s) {
                if (s.isBlank()) {
                    return true;
                }
            } else if (o instanceof Collection<?> c) {
                if (c.isEmpty()) {
                    return true;
                }
            } else if (o instanceof Map<?, ?> m) {
                if (m.isEmpty()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * 检查身份证号是否合法
     */
    public static boolean validIdNum(String idNum) {
        return idNum != null &&
                idNum.length() == 18 &&
                (Strs.isNum(idNum) || (Strs.isNum(idNum.substring(0, 17)) && Character.isLetter(idNum.charAt(17))));
    }
}
