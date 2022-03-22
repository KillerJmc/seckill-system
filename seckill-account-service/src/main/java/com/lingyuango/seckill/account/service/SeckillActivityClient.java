package com.lingyuango.seckill.account.service;

import com.jmc.net.R;
import com.lingyuango.seckill.account.pojo.SeckillActivityRule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "seckill-service", path = "/seckillActivity", contextId = "seckillActivityFeignClient")
public interface SeckillActivityClient {
    /**
     * 获取当前秒杀规则
     */
    @PostMapping("/getRule")
    R<SeckillActivityRule> getRule();

    /**
     * 获取当前秒杀id
     */
    @PostMapping("/getSeckillId")
    R<Integer> getSeckillId();
}
