package com.lingyuango.seckill.payment.client;

import com.lingyuango.seckill.payment.common.Const;
import com.lingyuango.seckill.payment.pojo.MockAccount;
import com.lingyuango.seckill.payment.pojo.MockPayInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;

@FeignClient(
        name = "mock-service",
        url = "${spring.cloud.openfeign.client.config.mock-service.url}",
        contextId = "payClient"
)
public interface PayClient {

    @PostMapping("/pay")
    feign.Response pay(@RequestHeader("Appid") String appid,
                       @RequestHeader("Date-Stamp") @DateTimeFormat(pattern = Const.DATE_TIME_FORMAT) LocalDateTime date,
                       @RequestHeader("Signature") String signature,
                       @RequestBody MockPayInfo inf);

    @PostMapping("/checkInformation")
    feign.Response checkInformation(@RequestHeader("Appid") String appid,
                                    @RequestHeader("Date-Stamp") @DateTimeFormat(pattern = Const.DATE_TIME_FORMAT) LocalDateTime date,
                                    @RequestHeader("Signature") String signature,
                                    @RequestBody MockAccount inf);
}
