package com.lingyuango.seckill.account.service.impl;

import com.jmc.lang.Objs;
import com.jmc.lang.Strs;
import com.lingyuango.seckill.account.common.Const;
import com.lingyuango.seckill.account.common.MsgMapping;
import com.lingyuango.seckill.account.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Stack;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class TokenServiceImpl implements TokenService {
    private final StringRedisTemplate redisTemplate;

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

    @Override
    public void createAndSetCookies(Integer account, HttpServletRequest req, HttpServletResponse resp) {
        // 定义token
        var token = UUID.randomUUID().toString();

        // 存入redis
        putAccount(token, account);

        // 获取二级域名
        var secondDomain = getSecondDomain(req.getServerName());

        // token的cookie
        var tokenCookie = ResponseCookie
                .from(Const.COOKIE_TOKEN_NAME, token)
                .sameSite("Lax")
                .domain(secondDomain)
                .path("/")
                .build();

        // 账号的cookie
        var accountCookie = ResponseCookie
                .from(Const.COOKIE_ACCOUNT_NAME, account.toString())
                .sameSite("Lax")
                .domain(secondDomain)
                .path("/")
                .build();

        // 添加cookie
        resp.addHeader(HttpHeaders.SET_COOKIE, tokenCookie.toString());
        resp.addHeader(HttpHeaders.SET_COOKIE, accountCookie.toString());
    }

    @Override
    public void putAccount(String token, Integer account) {
        redisTemplate.opsForValue().set(Const.REDIS_TOKEN_GROUP + token, String.valueOf(account));
    }

    @Override
    public Integer getAccount(String token) {
        if (Objs.nullOrEmpty(token)) {
            return null;
        }

        var account = redisTemplate.opsForValue().get(Const.REDIS_TOKEN_GROUP + token);
        return account == null ? null : Strs.isNum(account) ? Integer.valueOf(account) : null;
    }

    @Override
    public void delete(String token) throws Exception {
        if (Boolean.FALSE.equals(redisTemplate.delete(Const.REDIS_TOKEN_GROUP + token))) {
            throw new Exception(MsgMapping.INVALID_TOKEN);
        }
    }
}
