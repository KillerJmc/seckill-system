package com.lingyuango.seckill.payment.client;

import com.lingyuango.seckill.payment.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "seckill-service")
public interface ActivityFeignService {

    @PostMapping("/getProduct")
    Product getProduct(Integer seckillId);
}
