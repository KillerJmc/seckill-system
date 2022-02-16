package com.lingyuango.seckill.service;

import com.lingyuango.seckill.pojo.SeckillActivity;

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
    Integer getLatestSeckillId();
}
