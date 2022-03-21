package com.lingyuango.seckill.payment.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.Storage;
import com.lingyuango.seckill.payment.service.StorageService;
import com.lingyuango.seckill.payment.dao.StorageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageDao storageDao;

    public R getStorage(Integer seckillId) {
        var storage = storageDao.selectOne(Wrappers.<Storage>lambdaQuery().eq(Storage::getSeckillId, seckillId));
        return R.ok().data(storage);
    }

    @Override
    public synchronized Boolean decrease(Integer seckillId) {
        var storage = storageDao.selectOne(Wrappers.<Storage>lambdaQuery().eq(Storage::getSeckillId, seckillId));
        storage.setAmount(storage.getAmount() - 1);
        return storageDao.updateById(storage) != 0;
    }
}
