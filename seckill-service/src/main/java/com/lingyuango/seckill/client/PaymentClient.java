package com.lingyuango.seckill.client;

import com.jmc.net.R;
import com.lingyuango.seckill.pojo.BasicOrder;
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
}
