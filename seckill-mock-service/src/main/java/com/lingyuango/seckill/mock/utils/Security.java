package com.lingyuango.seckill.mock.utils;

import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * @author ChaconneLuo
 */
public class Security {

    private Security() {

    }

    public static <T> Boolean verify(String appid, String secKey, LocalDateTime date, String signature, T t) {
        var newSignature = getSignature(appid, secKey, date, t);
        return newSignature.equals(signature);
    }

    public static <T> String getSignature(String appid, String secKey, LocalDateTime date, T t) {
        String s = "appid" + appid + "date" + date.toInstant(ZoneOffset.of("+8")).toEpochMilli() + t.toString();
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secKey).hmacHex(s);
    }
}
