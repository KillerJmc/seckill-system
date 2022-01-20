package com.seckill.util;

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
}
