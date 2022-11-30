package com.lingyuango.seckill.payment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmc.net.R;
import com.lingyuango.seckill.payment.common.Const;
import com.lingyuango.seckill.payment.common.MsgMapping;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import com.lingyuango.seckill.payment.service.OrderService;
import com.lingyuango.seckill.payment.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lingyuango
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")

public class PaymentController {

    private final OrderService orderService;
    private final RedisService redisService;
    private final StreamBridge streamBridge;

    /**
     * 下订单
     */
    @PostMapping("/placeOrder")
    public R<Void> placeOrder(@RequestBody BasicOrder msg) {
        streamBridge.send(Const.ORDER_IN_BINDING, msg);
        return R.ok().build();
    }

    /**
     * 尝试支付
     */
    @PostMapping("/requestForPay")
    public R<Void> requestForPay(String orderId) {
        if (orderService.OrderOverTimeCheck(orderId)) {
            streamBridge.send(Const.PAY_IN_BINDING, orderId);

            return R.ok().build();
        } else {
            redisService.putMessage(orderId, MsgMapping.ORDER_OVERTIME);
            return R.error(MsgMapping.ORDER_OVERTIME);
        }


    }

    /**
     * 获取订单信息
     */
    @PostMapping("/getOrder")
    R<BasicOrder> getOrder(Integer accountId) throws JsonProcessingException {
        return redisService.getBasicOrder(accountId);
    }


    /**
     * 获取订单支付状态
     */
    @PostMapping("/getPaymentStatus")
    R<PaymentStatus> getPaymentStatus(String orderId) throws JsonProcessingException {
        return redisService.getPaymentStatus(orderId);
    }

}
