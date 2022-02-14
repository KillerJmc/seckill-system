package com.seckill.service.impl;

import com.jmc.lang.Strs;
import com.seckill.common.Const;
import com.seckill.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final StringRedisTemplate redisTemplate;

    @Override
    public String create(Integer accountId) {
        // 定义token
        var token = UUID.randomUUID().toString();

        // 存入redis
        putAccountId(token, accountId);

        return token;
    }

    @Override
    public void putAccountId(String token, Integer accountId) {
        redisTemplate.opsForValue().set(Const.REDIS_TOKEN_GROUP + token, String.valueOf(accountId));
    }

    @Override
    public Integer getAccountId(String token) {
        String res;
        return (res = redisTemplate.opsForValue().get(Const.REDIS_TOKEN_GROUP + token)) == null ? null :
                Strs.isNum(res) ? Integer.valueOf(res) : null;
    }
}
