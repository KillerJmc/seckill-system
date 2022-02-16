package com.lingyuango.seckill.service;

/**
 * @author Jmc
 */
public interface StorageService {
    /**
     * 获取当前的库存数量
     */
    Integer getStorage(Integer seckillId);

    /**
     * 减少库存
     */
    int decrease(Integer seckillId);
}
