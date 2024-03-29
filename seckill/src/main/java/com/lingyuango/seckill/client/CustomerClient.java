package com.lingyuango.seckill.client;

import com.jmc.net.R;
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
    /**
     * 用户是否能申请活动
     */
    @GetMapping("/canApply")
    R<Boolean> canApply(@RequestParam("account") Integer account);
}
