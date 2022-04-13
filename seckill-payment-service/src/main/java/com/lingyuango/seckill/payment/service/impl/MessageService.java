package com.lingyuango.seckill.payment.service.impl;

import com.jmc.lang.Tries;
import com.lingyuango.seckill.payment.common.MsgMapping;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import com.lingyuango.seckill.payment.service.OrderService;
import com.lingyuango.seckill.payment.service.PayService;
import com.lingyuango.seckill.payment.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.function.Consumer;


/**
 * @author Lingyuango
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {

    private final OrderService orderService;
    private final PayService payService;
    private final RedisService redisService;

    @Bean
    public Consumer<BasicOrder> orderHandle() {
        return basicOrder -> {
            log.info("Handle Order: {}", basicOrder);
            var order = orderService.insert(basicOrder);
            boolean payStatus;
            payStatus = order != null;
            assert order != null;
            Tries.tryThis(() -> redisService.putBasicOrder(new BasicOrder() {{
                setOrderId(order.getOrderId());
                setAccountId(basicOrder.getAccountId());
                setPutOrderSuccess(payStatus);
                setMoney(basicOrder.getMoney());
            }}));
        };
    }

    @Bean
    public Consumer<String> payHandle() {
        return orderId -> {
            var rValue = Tries.tryReturnsT(() -> payService.pay(orderId));
            if (rValue.getData() != null) {
                Tries.tryThis(() -> redisService.putPaymentStatus(Objects.requireNonNullElseGet(rValue.get(), () -> new PaymentStatus() {{
                    setPaymentSuccess(false);
                    setOrderId(orderId);
                }})));
            } else {
                redisService.putMessage(orderId, rValue.getMessage());
            }
        };
    }
}
