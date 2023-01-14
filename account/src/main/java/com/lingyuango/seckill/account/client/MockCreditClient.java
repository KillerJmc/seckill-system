package com.lingyuango.seckill.account.client;

import com.jmc.net.R;
import com.lingyuango.seckill.account.pojo.MockCreditInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(
        name = "mock-service",
        url = "${spring.cloud.openfeign.client.config.mock-service.url}",
        path = "/mock",
        contextId = "mockCreditClient"
)
public interface MockCreditClient {
    @RequestMapping("/getCreditInfo")
    R<MockCreditInfo> getCreditInfo();
}
