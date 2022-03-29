package com.lingyuango.seckill.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
import com.lingyuango.seckill.service.PaymentCallbackService;
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
    private final PaymentCallbackService paymentCallbackService;

    /**
     * 存放用户订单到redis
     */
    @PostMapping("/putOrder")
    public void putOrder(BasicOrder basicOrder) throws JsonProcessingException {
        paymentCallbackService.putOrderToRedis(basicOrder);
    }

    /**
     * 存放支付状态信息到redis
     */
    @PostMapping("/putPaymentStatus")
    public void putPaymentStatus(PaymentStatus paymentStatus) throws JsonProcessingException {
        paymentCallbackService.putPaymentStatusToRedis(paymentStatus);
    }
}
