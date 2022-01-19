package com.seckill.service;

import com.seckill.pojo.Storage;

/**
 * @author Jmc
 */
public interface StorageService {
    int insert(Storage storage);

    /**
     * 减少库存
     */
    int decrease(Integer seckillId);
}
