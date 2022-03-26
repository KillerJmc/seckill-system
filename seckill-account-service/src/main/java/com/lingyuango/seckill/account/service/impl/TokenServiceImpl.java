package com.lingyuango.seckill.account.service.impl;

import com.jmc.lang.Objs;
import com.jmc.lang.Strs;
import com.lingyuango.seckill.account.common.Const;
import com.lingyuango.seckill.account.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final StringRedisTemplate redisTemplate;

    @Override
    public Cookie create(Integer accountId) {
        // 定义token
        var token = UUID.randomUUID().toString();

        // 存入redis
        putAccountId(token, accountId);

        // 设置cookie
        var cookie = new Cookie(Const.TOKEN_COOKIE_NAME, token);

        // 设置路径为根路径，对所有url可见
        cookie.setPath("/");

        return cookie;
    }

    @Override
    public void putAccountId(String token, Integer accountId) {
        redisTemplate.opsForValue().set(Const.REDIS_TOKEN_GROUP + token, String.valueOf(accountId));
    }

    @Override
    public Integer getAccountId(String token) {
        if (Objs.nullOrEmpty(token)) {
            return null;
        }

        String res;
        return (res = redisTemplate.opsForValue().get(Const.REDIS_TOKEN_GROUP + token)) == null ? null :
                Strs.isNum(res) ? Integer.valueOf(res) : null;
    }
}
