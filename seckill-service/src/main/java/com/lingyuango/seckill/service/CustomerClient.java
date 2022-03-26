package com.lingyuango.seckill.service;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seckill-account-service", path = "/customer", contextId = "CustomerFeignClient")
public interface CustomerClient {
    /**
     * 用户是否能申请活动
     */
    @PostMapping("/canApply")
    R<Boolean> canApply(@RequestParam("customerId") Integer customerId);
}
