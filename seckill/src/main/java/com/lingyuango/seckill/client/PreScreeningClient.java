package com.lingyuango.seckill.client;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        value = "account-service",
        url = "${spring.cloud.openfeign.client.config.account-service.url}",
        path = "/expose/preScreening",
        contextId = "preScreenClient"
)
public interface PreScreeningClient {
    @PostMapping("/insert")
    R<Void> insert(@RequestParam("account") Integer account);
}
