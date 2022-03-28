package com.lingyuango.seckill.client;

import com.jmc.net.R;
import com.lingyuango.seckill.pojo.SeckillSuccess;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seckill-payment-service", path = "/seckillSuccess", contextId = "seckillSuccessClient")
public interface SeckillSuccessClient {
    /**
     * 插入秒杀成功的一条客户信息
     */
    @PostMapping("/insert")
    R<SeckillSuccess> insert(@RequestParam("seckillId") Integer seckillId, @RequestParam("customerId") Integer customerId);

    /**
     * 判断某客户是否在秒杀成功表里
     */
    @PostMapping("/contains")
    R<Boolean> contains(@RequestParam("seckillId") Integer seckillId, @RequestParam("customerId") Integer customerId);
}
