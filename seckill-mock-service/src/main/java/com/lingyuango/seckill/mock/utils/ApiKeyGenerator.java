package com.lingyuango.seckill.mock.utils;

import org.springframework.util.DigestUtils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * @author ChaconneLuo
 */
public class ApiKeyGenerator {

    private ApiKeyGenerator() {

    }

    public static String secretKeyGenerator() {
        return "Secret_Key" + DigestUtils.md5DigestAsHex(
                new BigInteger(String.valueOf(RandomGeneratorTool.getRandomLong()))
                        .multiply(BigInteger.valueOf(RandomGeneratorTool.getRandomLong()))
                        .toString()
                        .getBytes(StandardCharsets.UTF_8));
    }

    public static String appIdGenerator() {
        return "Appid" + DigestUtils.md5DigestAsHex(
                new BigInteger(String.valueOf(RandomGeneratorTool.getRandomLong()))
                        .multiply(BigInteger.valueOf(RandomGeneratorTool.getRandomLong()))
                        .toString()
                        .getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        System.out.println(appIdGenerator());
        System.out.println(secretKeyGenerator());
    }
}
