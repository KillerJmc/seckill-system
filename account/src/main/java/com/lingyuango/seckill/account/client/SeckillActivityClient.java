package com.lingyuango.seckill.account.client;

import com.jmc.net.R;
import com.lingyuango.seckill.account.pojo.SeckillActivityRule;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "seckill-service",
        url = "${spring.cloud.openfeign.client.config.seckill-service.url}",
        path = "/expose/seckillActivity",
        contextId = "seckillActivityClient"
)
public interface SeckillActivityClient {
    /**
     * 获取当前秒杀规则
     */
    @GetMapping("/getRule")
    R<SeckillActivityRule> getRule();

    /**
     * 获取当前秒杀id
     */
    @GetMapping("/getSeckillId")
    R<Integer> getSeckillId();
}
