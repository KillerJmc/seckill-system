package com.lingyuango.seckill.payment.client;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.BasicOrder;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 返回给秒杀模块的回调接口
 */
@FeignClient(value = "seckill-service", path = "/paymentCallback", contextId = "callBackClient")
public interface CallBackClient {
    @PostMapping("/putOrder")
    void putOrder(@RequestBody BasicOrder basicOrder);

    @PostMapping("/putPaymentStatus")
    void putPaymentStatus(@RequestBody PaymentStatus paymentStatus);
}
