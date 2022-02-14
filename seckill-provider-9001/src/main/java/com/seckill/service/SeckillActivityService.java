package com.seckill.service;

import com.seckill.pojo.SeckillActivity;

/**
 * @author Jmc
 */
public interface SeckillActivityService {
    /**
     * 获取最新秒杀活动
     */
    SeckillActivity getLatest();

    /**
     * 获取最新秒杀id
     */
    int getLatestSeckillId();
}
