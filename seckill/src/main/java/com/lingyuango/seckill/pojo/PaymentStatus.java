package com.lingyuango.seckill.pojo;

import com.lingyuango.seckill.config.TypedReflective;
import lombok.Data;

/**
 * 返回的支付状态
 */
@Data
@TypedReflective
public class PaymentStatus {
    private String orderId;
    private Boolean paymentSuccess;
}
