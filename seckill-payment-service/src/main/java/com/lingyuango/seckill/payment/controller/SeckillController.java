package com.lingyuango.seckill.payment.controller;

import cn.hutool.core.util.ObjectUtil;
import com.jmc.net.R;
import com.lingyuango.seckill.payment.common.MsgMapping;
import com.lingyuango.seckill.payment.pojo.OrderMessage;
import com.lingyuango.seckill.payment.pojo.ReceivePayMessage;
import com.lingyuango.seckill.payment.producer.OrderProducer;
import com.lingyuango.seckill.payment.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

/**
 * @author Lingyuango
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class SeckillController {

    private final OrderProducer orderProducer;
    private final MessageService messageService;


    @PostMapping("/order")
    @ResponseBody
    public synchronized R order(@RequestBody OrderMessage msg) throws MQBrokerException, RemotingException, InterruptedException, MQClientException{
        var message = new Message("RequestMsg", "ORDER", ObjectUtil.serialize(msg));

        var send = orderProducer.getProducer().send(message);
        log.info(send.toString());
        return R.ok().msg(MsgMapping.ORDER_SEND_SUCCESS);
    }

    @PostMapping("/pay")
    @ResponseBody
    public synchronized R pay(@RequestBody ReceivePayMessage msg) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Message message = new Message("RequestMsg", "PAY", ObjectUtil.serialize(msg));

        var send = orderProducer.getProducer().send(message);
        log.info(send.toString());
        return R.ok().msg(MsgMapping.PAY_SEND_SUCCESS);
    }

}
