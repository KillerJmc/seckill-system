package com.lingyuango.seckill.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 支付模块返回的基础订单类
 * @author Jmc
 */
@Data
public class BasicOrder implements Serializable {
    /**
     * 秒杀id
     */
    private Integer seckillId;

    /**
     * 账号id
     */
    private Integer accountId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单金额
     */
    private Double money;

    /**
     * 是否下订单成功
     */
    private Boolean putOrderSuccess;
}
