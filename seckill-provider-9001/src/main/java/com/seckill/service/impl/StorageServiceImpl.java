package com.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.seckill.dao.StorageDao;
import com.seckill.pojo.Storage;
import com.seckill.service.StorageService;
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
    public int getStorage(Integer seckillId) {
        return -1;
    }

    @Override
    public int decrease(Integer seckillId) {
        return 0;
    }
}
