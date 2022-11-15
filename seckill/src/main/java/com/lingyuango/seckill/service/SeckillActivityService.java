package com.lingyuango.seckill.service;

import com.jmc.lang.ref.Pointer;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
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
    boolean seckillNotStarted();

    /**
     * 获取最新秒杀活动规则
     */
    SeckillActivityRule getRule() throws Exception;

    /**
     * 获取最新秒杀id
     */
    Integer getLatestSeckillId();

    /**
     * 利用线程池异步发送消息下单
     * @param seckillUrl 秒杀url
     * @param account 下订单的账号
     */
    void placeOrderAsync(String seckillUrl, Integer account);

    /**
     * 初始化在Redis中秒杀活动的基本信息
     */
    void initRedis();

    /**
     * 从缓存中获取秒杀id
     */
    String getSeckillIdFromRedis();

    /**
     * 从缓存中获取订单金额
     */
    Double getOrderPriceFromRedis(String seckillUrl);

    /**
     * 判断订单号是否非法
     */
    boolean isInvalidOrderId(Integer accountId, String orderId);

    /**
     * 商品是否已经卖完
     */
    boolean isSoldOut();

    /**
     * 判断秒杀链接是否非法
     */
    boolean isInvalidSeckillUrl(String seckillUrl);

    /**
     * 从redis中检查是否已经秒杀成功
     */
    boolean hasSeckillSuccess(String seckillId, Integer accountId);

    /**
     * 判断是否已经付款
     */
    boolean hasAlreadyPaid(Integer accountId);

    /**
     * 尝试扣库存
     */
    void decreaseStorage(String seckillId, Integer accountId) throws Exception;

    /**
     * 尝试获取订单
     */
    BasicOrder getOrder(String seckillId, Integer accountId);

    /**
     * 获取支付状态信息
     * @param errorMsgPtr 错误信息指针
     */
    PaymentStatus getPaymentStatus(Integer accountId, String orderId, Pointer<String> errorMsgPtr);
}
