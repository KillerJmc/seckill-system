package com.lingyuango.seckill.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.pojo.BasicOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付模块回调
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/paymentCallback")
@Slf4j
public class PaymentCallbackController {
    @PostMapping("/putOrder")
    public void putOrder(BasicOrder basicOrder) {

    }

    @PostMapping("/putPaymentStatus")
    public void putPaymentStatus(String orderId) {

    }
}
