package com.lingyuango.seckill.payment.pojo;

import com.lingyuango.seckill.payment.config.TypedReflective;
import lombok.Data;

import java.io.Serializable;

/**
 * 放队列中的基础订单类
 * @author ChaconneLuo
 */
@Data
@TypedReflective
public class BasicOrder implements Serializable {
    private Integer seckillId;
    private Integer accountId;
    private String orderId;
    private Double money;
    private Boolean putOrderSuccess;
}
