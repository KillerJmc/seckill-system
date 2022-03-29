package com.lingyuango.seckill.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
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
    /**
     * 存放用户订单到redis
     */
    @PostMapping("/putOrder")
    public void putOrder(BasicOrder basicOrder) {

    }

    /**
     * 存放支付成功与否信息到redis
     */
    @PostMapping("/putPaymentStatus")
    public void putPaymentStatus(PaymentStatus paymentStatus) {

    }
}
