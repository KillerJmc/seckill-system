package com.seckill.service;

import com.seckill.pojo.SeckillActivity;

import java.util.List;

/**
 * @author Jmc
 */
public interface SeckillActivityService {
    /**
     * 插入一条秒杀活动
     */
    void insert(SeckillActivity seckillActivity);

    /**
     * 获取秒杀活动
     */
    SeckillActivity getOne();
}
