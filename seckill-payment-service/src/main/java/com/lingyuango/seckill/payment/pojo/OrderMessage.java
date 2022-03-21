package com.lingyuango.seckill.payment.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author ChaconneLuo
 */
@Data
@ToString
public class OrderMessage implements Serializable {

    private Integer seckillId;

    private Integer accountId;

}
