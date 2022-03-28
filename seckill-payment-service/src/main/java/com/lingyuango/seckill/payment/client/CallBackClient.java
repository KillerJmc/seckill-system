package com.lingyuango.seckill.payment.client;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 返回给秒杀模块的回调接口
 */
@FeignClient(value = "seckill-service", path = "/seckillActivity", contextId = "callBackClient")
public interface CallBackClient {
    @PostMapping("/putOrder")
    void putOrder(@RequestBody MessageReturn msg);

    @PostMapping("/putPaymentStatus")
    void putPaymentStatus(@RequestBody R msg);
}
