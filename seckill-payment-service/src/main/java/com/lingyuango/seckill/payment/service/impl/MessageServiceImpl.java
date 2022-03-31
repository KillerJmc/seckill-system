package com.lingyuango.seckill.payment.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.service.MessageService;
import com.lingyuango.seckill.payment.dao.OrderDao;
import com.lingyuango.seckill.payment.service.OrderService;
import com.lingyuango.seckill.payment.service.PayService;
import com.lingyuango.seckill.payment.client.CallBackClient;
import com.lingyuango.seckill.payment.service.RedisService;
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
    private final CallBackClient callBackClient;
    private final RedisService redisService;

    @Async
    public void OrderHandle(BasicOrder basicOrder) {
        var orderId = orderService.insert(basicOrder);
        boolean payStatus;
        payStatus= orderId != null;
//        callBackClient.putOrder(new BasicOrder() {{
//            setOrderId(orderId);
//            setPutOrderSuccess(payStatus);
//        }});
        redisService.putBasicOrder(new BasicOrder() {{
            setOrderId(orderId);
            setPutOrderSuccess(payStatus);
        }});
    }

    @Async
    public void PayHandle(String orderId) throws IOException {
        var pay = payService.pay(orderId).get();
        //callBackClient.putPaymentStatus(pay);
        redisService.putPaymentStatus(pay);
    }


    @Override
    public void onMessage(MessageExt message) {
        var obj = ObjectUtil.deserialize(message.getBody());
        if (message.getTags().equals("ORDER")) {
            OrderHandle((BasicOrder) obj);
        } else {
            try {
                PayHandle((String) obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
