package com.lingyuango.seckill.payment.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 放队列中的基础订单类
 * @author ChaconneLuo
 */
@Data
public class BasicOrder implements Serializable {
    private Integer seckillId;
    private Integer accountId;
}
