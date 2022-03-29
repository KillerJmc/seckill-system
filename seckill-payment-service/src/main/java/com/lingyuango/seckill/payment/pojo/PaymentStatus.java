package com.lingyuango.seckill.payment.pojo;

import lombok.Data;

/**
 * 返回的支付状态
 */
@Data
public class PaymentStatus {
    private String orderId;
    private Boolean paymentSuccess;
}
