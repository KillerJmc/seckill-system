package com.lingyuango.seckill.payment.controller;

import cn.hutool.core.util.ObjectUtil;
import com.jmc.net.R;
import com.lingyuango.seckill.payment.common.MsgMapping;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import com.lingyuango.seckill.payment.producer.OrderProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

/**
 * @author Lingyuango
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {
    private final OrderProducer orderProducer;

    /**
     * 下订单
     */
    @PostMapping("/placeOrder")
    public R<Void> placeOrder(@RequestBody BasicOrder msg) throws MQBrokerException, RemotingException, InterruptedException, MQClientException{
        var message = new Message("RequestMsg", "ORDER", ObjectUtil.serialize(msg));

        var send = orderProducer.getProducer().send(message);
        log.info(send.toString());
        return R.ok()
                .msg(MsgMapping.ORDER_SEND_SUCCESS)
                .build();
    }

    /**
     * 尝试支付
     */
    @PostMapping("/requestForPay")
    public R<Void> requestForPay(@RequestBody String orderId) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Message message = new Message("RequestMsg", "PAY", ObjectUtil.serialize(orderId));

        var send = orderProducer.getProducer().send(message);
        log.info(send.toString());
        return R.ok()
                .msg(MsgMapping.PAY_SEND_SUCCESS)
                .build();
    }

    /**
     * 获取订单信息
     */
    R<BasicOrder> getOrder(Integer accountId);


    /**
     * 获取订单支付状态
     */
    R<PaymentStatus> getPaymentStatus(String orderId);

}
