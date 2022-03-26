package com.lingyuango.seckill.service;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seckill-account-service", path = "/preScreening", contextId = "preScreenFeignClient")
public interface PreScreeningClient {
    @PostMapping("/insert")
    R<Void> insert(@RequestParam("customerId") Integer customerId);
}
