package com.lingyuango.seckill.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀成功客户表
 * @author Jmc
 */
@Data
public class SeckillSuccess {
    private Integer id;

    /**
     * 秒杀活动id
     */
    private Integer seckillId;

    /**
     * 客户id
     */
    private Integer accountId;

    /**
     * 订单id
     */
    private String orderId;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
