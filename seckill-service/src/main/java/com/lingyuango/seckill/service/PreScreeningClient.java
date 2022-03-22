package com.lingyuango.seckill.service;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "seckill-service", path = "/preScreening", contextId = "preScreenFeignClient")
public interface PreScreeningClient {
    @PostMapping("/insert")
    R<Void> insert(Integer customerId);
}
