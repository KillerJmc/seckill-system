package com.lingyuango.seckill.account.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;

/**
 * Cookie操作类
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Cookies {
    /**
     * 请求对象
     */
    private HttpServletRequest req;

    /**
     * 响应对象
     */
    private HttpServletResponse resp;

    /**
     * 绑定请求和响应对象来获取操作类实例
     * @param req 请求对象
     * @param resp 响应对象
     * @return 实例
     */
    public static Cookies bind(HttpServletRequest req, HttpServletResponse resp) {
        return new Cookies(req, resp);
    }

    /**
     * 添加cookie到客户端
     * @param name cookie名称
     * @param value cookie值
     * @return 本身实例
     */
    public Cookies add(String name, String value) {
        var secondDomain = URLUtil.getSecondDomain(req.getServerName());

        var cookie = ResponseCookie
                .from(name, value)
                .sameSite("Lax")
                .domain(secondDomain)
                .path("/")
                .build();

        resp.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return this;
    }

    /**
     * 从客户端中删除cookie
     * @param name cookie名称
     * @return 本身实例
     */
    public Cookies delete(String name) {
        var secondDomain = URLUtil.getSecondDomain(req.getServerName());

        var cookie = ResponseCookie
                .from(name, "")
                .sameSite("Lax")
                .domain(secondDomain)
                .path("/")
                .maxAge(0)
                .build();

        resp.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return this;
    }
}
