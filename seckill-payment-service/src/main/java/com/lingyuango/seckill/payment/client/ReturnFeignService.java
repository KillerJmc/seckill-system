package com.lingyuango.seckill.payment.client;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.MessageReturn;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@FeignClient("seckill-service")
public interface ReturnFeignService {
    @PostMapping("/orderMsg")
    void sendOrderMsg(@RequestBody MessageReturn msg);

    @PostMapping("/payMsg")
    void sendPayMsg(@RequestBody R msg);
}
