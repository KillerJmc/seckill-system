package com.lingyuango.seckill.payment.service.impl;

import com.lingyuango.seckill.payment.dao.StorageDao;
import com.lingyuango.seckill.payment.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageDao storageDao;

    @Override
    public synchronized void decrease(Integer seckillId) {
        var storage = storageDao.getOneBySeckillId(seckillId);
        storage.setAmount(storage.getAmount() - 1);
        storageDao.save(storage);
    }
}
