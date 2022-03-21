package com.lingyuango.seckill.payment.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lingyuango.seckill.payment.pojo.MessageReturn;
import com.lingyuango.seckill.payment.pojo.OrderMessage;
import com.lingyuango.seckill.payment.pojo.ReceivePayMessage;
import com.lingyuango.seckill.payment.service.MessageService;
import com.lingyuango.seckill.payment.dao.OrderDao;
import com.lingyuango.seckill.payment.service.OrderService;
import com.lingyuango.seckill.payment.service.PayService;
import com.lingyuango.seckill.payment.client.ReturnFeignService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * @author Lingyuango
 */

@Service
@RequiredArgsConstructor
@EnableAsync
@RocketMQMessageListener(topic = "RequestMsg", consumerGroup = "SECKILL_CONSUMER_GROUP")
public class MessageServiceImpl implements MessageService, RocketMQListener<MessageExt> {

    private final OrderDao orderDao;
    private final OrderService orderService;
    private final PayService payService;
    private final ReturnFeignService returnFeignService;

    @Async
    public void OrderHandle(OrderMessage orderMessage) {
        var orderId = orderService.insert(orderMessage);
        if (orderId != null) {
            returnFeignService.sendOrderMsg(new MessageReturn() {{
                setOrderId(orderId);
                setBuildSuccess(true);
            }});
        } else {
            returnFeignService.sendOrderMsg(new MessageReturn() {{
                setOrderId(null);
                setBuildSuccess(false);
            }});
        }
    }

    @Async
    public void PayHandle(ReceivePayMessage receivePayMessage) throws IOException {
        var pay = payService.pay(receivePayMessage);
        returnFeignService.sendPayMsg(pay);
    }


    @Override
    public void onMessage(MessageExt message) {
        var obj = ObjectUtil.deserialize(message.getBody());
        if (message.getTags().equals("ORDER")) {
            OrderHandle((OrderMessage) obj);
        } else {
            try {
                PayHandle((ReceivePayMessage) obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
