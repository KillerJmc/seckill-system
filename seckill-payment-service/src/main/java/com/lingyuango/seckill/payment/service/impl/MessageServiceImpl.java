package com.lingyuango.seckill.payment.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import com.lingyuango.seckill.payment.service.MessageService;
import com.lingyuango.seckill.payment.dao.OrderDao;
import com.lingyuango.seckill.payment.service.OrderService;
import com.lingyuango.seckill.payment.service.PayService;
import com.lingyuango.seckill.payment.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;


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
    private final RedisService redisService;

    @Async
    public void OrderHandle(BasicOrder basicOrder) throws JsonProcessingException {
        var order = orderService.insert(basicOrder);
        boolean payStatus;
        payStatus = order != null;
        assert order != null;
        redisService.putBasicOrder(new BasicOrder() {{
            setOrderId(order.getOrderId());
            setAccountId(basicOrder.getAccountId());
            setPutOrderSuccess(payStatus);
            setMoney(basicOrder.getMoney());
        }});
    }

    @Async
    public void PayHandle(String orderId) throws IOException {
        var rValue = payService.pay(orderId);
        var pay = rValue.get();
        redisService.putPaymentStatus(Objects.requireNonNullElseGet(pay, () -> new PaymentStatus() {{
            setPaymentSuccess(false);
            setOrderId(orderId);
        }}));
    }


    @Override
    public void onMessage(MessageExt message) {
        var obj = ObjectUtil.deserialize(message.getBody());
        if (message.getTags().equals("ORDER")) {
            try {
                OrderHandle((BasicOrder) obj);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PayHandle((String) obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
