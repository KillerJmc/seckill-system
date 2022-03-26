package com.lingyuango.seckill.service;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seckill-payment-service", path = "/storage", contextId = "StorageFeignClient")
public interface StorageClient {
    /**
     * 获取库存
     */
    @PostMapping("/getStorage")
    R<Integer> getStorage(@RequestParam("seckillId") Integer seckillId);

    /**
     * 减少库存
     */
    @PostMapping("/decrease")
    R<Boolean> decrease(@RequestParam("seckillId") Integer seckillId);
}
