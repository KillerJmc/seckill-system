package com.lingyuango.seckill.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmc.net.R;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seckill-payment-service", path = "/payment", contextId = "paymentClient")
public interface PaymentClient {
    /**
     * 下订单
     */
    @PostMapping("/placeOrder")
    R<Void> placeOrder(@RequestBody BasicOrder msg);

    /**
     * 尝试支付
     */
    @PostMapping("/requestForPay")
    R<Void> requestForPay(@RequestBody String orderId);

    /**
     * 获取订单信息
     */
    R<BasicOrder> getOrder(Integer accountId);

    /**
     * 获取订单支付状态
     */
    R<PaymentStatus> getPaymentStatus(String orderId);
}
