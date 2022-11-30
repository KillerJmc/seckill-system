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
     * 获取秒杀活动倒计时
     * @return 倒计时（秒）
     */
    Long getCountDown();

    /**
     * 判断秒杀活动时候还没开始
     */
    boolean isSeckillNotStarted();

    /**
     * 获取最新秒杀活动规则
     */
    SeckillActivityRule getRule() throws Exception;

    /**
     * 获取最新秒杀id
     */
    Integer getLatestSeckillId();

    /**
     * 初始化在Redis中秒杀活动的基本信息
     */
    void initRedis();

    /**
     * 从缓存中获取秒杀url
     */
    String getSeckillUrlFromRedis();

    /**
     * 从缓存中获取订单金额
     */
    Double getOrderPriceFromRedis(String seckillUrl);

    /**
     * 判断秒杀链接是否非法
     */
    boolean isInvalidSeckillUrl(String seckillUrl);

    /**
     * 从redis中检查是否已经秒杀成功
     */
    boolean hasSeckillSuccess(Integer account);

    /**
     * 秒杀方法
     */
    void seckill(String seckillUrl, Integer account) throws Exception;

    /**
     * 尝试扣库存
     */
    void decreaseStorage(String seckillId, Integer account) throws Exception;
}
