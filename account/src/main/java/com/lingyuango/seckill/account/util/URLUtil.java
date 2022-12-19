package com.lingyuango.seckill.account.util;

import java.util.Stack;

/**
 * URL工具类
 * @author Jmc
 */
public class URLUtil {
    /**
     * 通过服务器名称获取二级域名
     * @param serverName 服务器名称
     * @return 二级域名
     */
    public static String getSecondDomain(String serverName) {
        // 域名列表
        var domains = new Stack<String>();

        // 利用"."分割域名
        int startIdx = 0, indexOfDot;
        while ((indexOfDot = serverName.indexOf(".", startIdx)) != -1) {
            domains.add(serverName.substring(startIdx, indexOfDot));
            startIdx = indexOfDot + 1;
        }
        domains.add(serverName.substring(startIdx));

        // 获取二级域名
        if (domains.size() == 1) {
            return domains.pop();
        } else {
            var firstDomain = domains.pop();
            var secondDomain = domains.pop();

            return ".%s.%s".formatted(secondDomain, firstDomain);
        }
    }
}
