package com.lingyuango.seckill.payment.client;

import com.lingyuango.seckill.payment.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "seckill-service", path = "/seckillActivity", contextId = "seckillActivityClient")
public interface SeckillActivityClient {

    @PostMapping("/getProduct")
    Product getProduct(Integer seckillId);
}
