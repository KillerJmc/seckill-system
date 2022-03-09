package com.lingyuango.seckill.mock.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.mock.dao.SecretKeyDao;
import com.lingyuango.seckill.mock.pojo.SecretKey;
import com.lingyuango.seckill.mock.service.SecretKeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ChaconneLuo
 */
@Service
@RequiredArgsConstructor
public class SecretKeyServiceImpl implements SecretKeyService {

    private final SecretKeyDao secretKeyDao;

    @Override
    public String getSecretKey(String appId) {
        var secretKey = secretKeyDao.selectOne(Wrappers.<SecretKey>lambdaQuery().eq(SecretKey::getAppId, appId));
        if (secretKey != null) {
            return secretKey.getSecretKey();
        } else {
            return null;
        }
    }
}
