package com.seckill.service.impl;

import com.seckill.common.Const;
import com.seckill.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final StringRedisTemplate redisTemplate;

    @Override
    public void putAccountName(String token, String accountName) {
        redisTemplate.opsForValue().set(Const.REDIS_TOKEN_GROUP + token, accountName);
    }

    @Override
    public String getAccountName(String token) {
        return redisTemplate.opsForValue().get(Const.REDIS_TOKEN_GROUP + token);
    }
}
