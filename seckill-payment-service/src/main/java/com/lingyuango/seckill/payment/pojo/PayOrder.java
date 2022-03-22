package com.lingyuango.seckill.payment.pojo;

import lombok.Data;
import lombok.ToString;


/**
 * @author ChaconneLuo
 */

@Data
@ToString
public class PayOrder {

    private Integer orderId;

    private Boolean paySuccess;

    private Integer accountId;

    private Integer money;

}