package com.lingyuango.seckill.gateway.service.impl;

import com.jmc.lang.Objs;
import com.jmc.lang.Strs;
import com.lingyuango.seckill.gateway.common.Const;
import com.lingyuango.seckill.gateway.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final StringRedisTemplate redisTemplate;

    @Override
    public Integer getAccount(String token) {
        if (Objs.nullOrEmpty(token)) {
            return null;
        }

        var account = redisTemplate.opsForValue().get(Const.REDIS_TOKEN_GROUP + token);
        return account == null ? null : Strs.isNum(account) ? Integer.valueOf(account) : null;
    }
}
