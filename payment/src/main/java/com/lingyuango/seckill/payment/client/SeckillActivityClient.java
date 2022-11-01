package com.lingyuango.seckill.payment.client;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seckill-service", path = "/expose/seckillActivity", contextId = "seckillActivityClient")
public interface SeckillActivityClient {
    @PostMapping("/getProduct")
    R<Product> getProduct(@RequestParam("seckillId") Integer seckillId);
}
