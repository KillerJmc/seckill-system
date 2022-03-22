package com.lingyuango.seckill.payment.pojo;

import lombok.Data;

/**
 * @author ChaconneLuo
 */

@Data
public class MessageReturn {
    private String orderId;
    private Boolean buildSuccess;
}
