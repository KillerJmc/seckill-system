package com.lingyuango.seckill.service;

import com.lingyuango.seckill.pojo.SeckillActivity;
import com.lingyuango.seckill.pojo.SeckillActivityRule;

/**
 * @author Jmc
 */
public interface SeckillActivityService {
    /**
     * 获取最新秒杀活动
     */
    SeckillActivity getLatest();

    /**
     * 获取最新秒杀活动规则
     */
    SeckillActivityRule getRule();

    /**
     * 获取最新秒杀id
     */
    Integer getLatestSeckillId();
}
