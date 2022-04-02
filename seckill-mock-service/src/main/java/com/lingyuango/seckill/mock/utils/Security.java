package com.lingyuango.seckill.mock.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * @author ChaconneLuo
 */
public class Security {

    private Security() {

    }

    public static <T> Boolean verify(String appid, String secKey, LocalDateTime date, String signature, T t) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        var newSignature = getSignature(appid, secKey, date, objectMapper.writeValueAsString(t));
        return newSignature.equals(signature);
    }

    public static <T> String getSignature(String appid, String secKey, LocalDateTime date, String t) {
        String s = "appid" + appid + "date" + date.toInstant(ZoneOffset.of("+8")).toEpochMilli() + t;
        return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, secKey).hmacHex(s);
    }

    public static <T> String getSignature(String appid, String secKey, LocalDateTime date, T t) throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        return getSignature(appid, secKey, date, objectMapper.writeValueAsString(t));
    }

}
