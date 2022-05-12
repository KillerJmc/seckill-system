package com.lingyuango.seckill.payment.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmc.net.HttpStatus;
import com.jmc.net.R;
import com.lingyuango.seckill.payment.common.Const;
import feign.Response;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

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
        String json = objectMapper.writeValueAsString(t);
        return getSignature(appid, secKey, date, json);
    }

    public static <T> T VerifyMapMessage(Response resp, Class<T> tClass) throws IOException {
        var headers = resp.headers();
        var objectMapper = new ObjectMapper();
        var check = objectMapper.readValue(resp.body().asInputStream().readAllBytes(), R.class);
        if (check.getCode() == HttpStatus.ERROR) {
            return null;
        }
        var map = (Map) check.getData();
        var result = objectMapper.convertValue(map.get("Result"), tClass);

        String dateStamp = headers.get("Date-Stamp").toArray(String[]::new)[0];
        String receiveSignature = headers.get("Signature").toArray(String[]::new)[0];
        Boolean isSafe = Security.verify(Const.Appid,
                Const.secKey,
                CheckDateStamp.convert(dateStamp),
                receiveSignature,
                map);
        if (isSafe) {
            return result;
        } else {
            return null;
        }

    }

}
