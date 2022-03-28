package com.lingyuango.seckill.payment.client;

import com.lingyuango.seckill.payment.pojo.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "seckill-service")
public interface AccountFeignService {
    @PostMapping("/getCustomer")
    Customer getCustomer(Integer accountId);
}
