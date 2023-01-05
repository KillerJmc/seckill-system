package com.lingyuango.seckill.account.service.impl;

import com.jmc.lang.Objs;
import com.jmc.lang.Strs;
import com.lingyuango.seckill.account.common.Const;
import com.lingyuango.seckill.account.common.MsgMapping;
import com.lingyuango.seckill.account.service.TokenService;
import com.lingyuango.seckill.account.util.Cookies;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class TokenServiceImpl implements TokenService {
    private final StringRedisTemplate redisTemplate;

    @Override
    public void addLoginCookies(Integer account, HttpServletRequest req, HttpServletResponse resp) {
        // 定义token
        var token = UUID.randomUUID().toString();

        // 存入redis
        putAccount(token, account);

        // 添加token和账户的cookie
        Cookies.bind(req, resp)
                .add(Const.COOKIE_TOKEN_NAME, token)
                .add(Const.COOKIE_ACCOUNT_NAME, account.toString());
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
    public void deleteLoginCookies(String token, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 从redis移除token
        if (Boolean.FALSE.equals(redisTemplate.delete(Const.REDIS_TOKEN_GROUP + token))) {
            throw new Exception(MsgMapping.INVALID_TOKEN);
        }

        // 移除cookie
        Cookies.bind(req, resp)
                .delete(Const.COOKIE_TOKEN_NAME)
                .delete(Const.COOKIE_ACCOUNT_NAME);
    }
}
