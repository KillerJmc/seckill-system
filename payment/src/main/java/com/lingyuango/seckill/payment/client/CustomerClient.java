package com.lingyuango.seckill.payment.client;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "account-service", path = "/expose/customer", contextId = "customerClient")
public interface CustomerClient {
    @PostMapping("/getCustomer")
    R<Customer> getCustomer(@RequestParam("accountId") Integer accountId);
}
