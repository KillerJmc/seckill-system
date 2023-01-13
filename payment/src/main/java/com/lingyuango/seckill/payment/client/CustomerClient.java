package com.lingyuango.seckill.payment.client;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "account-service",
        url = "${spring.cloud.openfeign.client.config.account-service.url}",
        path = "/expose/customer",
        contextId = "customerClient"
)
public interface CustomerClient {
    @GetMapping("/getByAccount")
    R<Customer> getByAccount(@RequestParam("account") Integer account);
}
