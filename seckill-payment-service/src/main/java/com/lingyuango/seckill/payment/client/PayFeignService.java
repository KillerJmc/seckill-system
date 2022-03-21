package com.lingyuango.seckill.payment.client;

import com.lingyuango.seckill.payment.pojo.CheckAccountInf;
import com.lingyuango.seckill.payment.pojo.PayInf;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.time.LocalDateTime;

@Component
@FeignClient(value = "seckill-mock-service")
public interface PayFeignService {

    @PostMapping("/pay")
    feign.Response pay(@RequestHeader("Appid") String appid,
                                           @RequestHeader("Date-Stamp") LocalDateTime date,
                                           @RequestHeader("Signature") String signature,
                                           @RequestBody PayInf inf);

    @PostMapping("/checkInformation")
    feign.Response checkInformation(@RequestHeader("Appid") String appid,
                                           @RequestHeader("Date-Stamp") LocalDateTime date,
                                           @RequestHeader("Signature") String signature,
                                           @RequestBody CheckAccountInf inf);
}
