package com.lingyuango.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.dao.StorageDao;
import com.lingyuango.seckill.pojo.Storage;
import com.lingyuango.seckill.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageDao storageDao;

    @Override
    public Integer getStorage(Integer seckillId) {
        var res = storageDao.selectOne(
                Wrappers.<Storage>lambdaQuery()
                        .select(Storage::getAmount)
                        .eq(Storage::getSeckillId, seckillId)
        );
        return res == null ? null : res.getAmount();
    }

    @Override
    public int decrease(Integer seckillId) {
        return storageDao.decrease(seckillId);
    }
}
