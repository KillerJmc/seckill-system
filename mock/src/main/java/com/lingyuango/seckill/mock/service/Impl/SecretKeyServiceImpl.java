package com.lingyuango.seckill.mock.service.Impl;

import com.lingyuango.seckill.mock.dao.SecretKeyDao;
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
        var secretKey = secretKeyDao.getOneByAppIdIs(appId);
        if (secretKey != null) {
            return secretKey.getSecretKey();
        } else {
            return null;
        }
    }
}
