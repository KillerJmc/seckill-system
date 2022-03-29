package com.lingyuango.seckill.client;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seckill-payment-service", path = "/storage", contextId = "storageClient")
public interface StorageClient {
    /**
     * 获取库存
     */
    @PostMapping("/get")
    R<Integer> getStorage(@RequestParam("seckillId") Integer seckillId);
}
