package com.lingyuango.seckill.payment.client;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "seckill-service",
        url = "${spring.cloud.openfeign.client.config.seckill-service.url}",
        path = "/expose/seckillActivity",
        contextId = "seckillActivityClient"
)
public interface SeckillActivityClient {
    @GetMapping("/getProduct")
    R<Product> getProduct(@RequestParam("seckillId") Integer seckillId);
}
