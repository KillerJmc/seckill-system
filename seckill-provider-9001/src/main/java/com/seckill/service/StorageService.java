package com.seckill.service;

import com.seckill.pojo.Storage;

/**
 * @author Jmc
 */
public interface StorageService {
    /**
     * 获取当前的库存数量
     */
    int getStorage(Integer seckillId);

    /**
     * 减少库存
     */
    int decrease(Integer seckillId);
}
