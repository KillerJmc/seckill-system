package com.lingyuango.seckill.account.client;

import com.jmc.net.R;
import com.lingyuango.seckill.account.pojo.MockCreditInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "mock-service", path = "/mock", contextId = "mockCreditClient")
public interface MockCreditClient {
    @RequestMapping("/getCreditInfo")
    R<MockCreditInfo> getCreditInfo();
}
