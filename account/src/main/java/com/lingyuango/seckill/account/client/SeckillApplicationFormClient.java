package com.lingyuango.seckill.account.client;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "seckill-service",
        url = "${spring.cloud.openfeign.client.config.seckill-service.url}",
        path = "/expose/seckillApplicationForm",
        contextId = "seckillApplicationFormClient"
)
public interface SeckillApplicationFormClient {
    /**
     * 判断某客户是否在申请表中
     */
    @GetMapping("/contains")
    R<Boolean> contains(@RequestParam("account") Integer account);
}
