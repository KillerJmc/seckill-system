package com.lingyuango.seckill.payment.client;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "seckill-account-service", path = "/customer", contextId = "customerClient")
public interface CustomerClient {
    @PostMapping("/getCustomer")
    R<Customer> getCustomer(Integer accountId);
}
