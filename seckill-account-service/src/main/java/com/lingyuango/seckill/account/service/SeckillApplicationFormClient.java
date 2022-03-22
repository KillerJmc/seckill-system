package com.lingyuango.seckill.account.service;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seckill-service", path = "/seckillApplicationForm", contextId = "seckillApplicationFormFeignClient")
public interface SeckillApplicationFormClient {
    /**
     * 判断某客户是否在申请表中
     */
    @PostMapping("/contains")
    R<Boolean> contains(@RequestParam("customerId") Integer customerId);
}
