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

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class TokenServiceImpl implements TokenService {
    private final StringRedisTemplate redisTemplate;

    @Override
    public void createAndSetCookies(Integer account, HttpServletResponse resp) {
        // 定义token
        var token = UUID.randomUUID().toString();

        // 存入redis
        putAccount(token, account);

        // token的cookie
        var tokenCookie = ResponseCookie
                .from(Const.COOKIE_TOKEN_NAME, token)
                .sameSite("Lax")
                .path("/")
                .build();

        // 账号的cookie
        var accountCookie = ResponseCookie
                .from(Const.COOKIE_ACCOUNT_NAME, account.toString())
                .sameSite("Lax")
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
