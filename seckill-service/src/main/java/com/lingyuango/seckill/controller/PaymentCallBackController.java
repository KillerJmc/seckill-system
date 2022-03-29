package com.lingyuango.seckill.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.pojo.BasicOrder;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 支付模块回调
 */
public class PaymentCallBackController {
    @PostMapping("/putOrder")
    R<Void> putOrder(BasicOrder basicOrder) {

    }

    @PostMapping("/putPaymentStatus")
    R<Void> putPaymentStatus(String orderId) {

    }
}
