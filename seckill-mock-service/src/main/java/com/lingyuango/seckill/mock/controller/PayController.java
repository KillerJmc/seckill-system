package com.lingyuango.seckill.mock.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.mock.pojo.PayInformation;
import com.lingyuango.seckill.mock.service.PayService;
import com.lingyuango.seckill.mock.service.SecretKeyService;
import com.lingyuango.seckill.mock.utils.CheckDateStamp;
import com.lingyuango.seckill.mock.utils.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * @author ChaconneLuo
 */
@Controller
@RequiredArgsConstructor
public class PayController {

    private SecretKeyService secretKeyService;
    private PayService payService;

    @PostMapping("/Pay")
    @ResponseBody
    public R pay(@RequestHeader("Appid") String appid,
                 @RequestHeader("Date-Stamp") LocalDateTime date,
                 @RequestHeader("Signature") String signature,
                 @RequestBody PayInformation pay,
                 HttpServletResponse resp) {
        var secKey = secretKeyService.getSecretKey(appid);
        if (Security.verify(appid, secKey, date, signature, pay)) {
            if (CheckDateStamp.CheckOverTime(date)) {
                var status = payService.Pay(pay);
                var nowDate = LocalDateTime.now();
                resp.addHeader("Appid", appid);
                resp.addHeader("Date-Stamp", String.valueOf(nowDate.toEpochSecond(ZoneOffset.of("+8"))));
                resp.addHeader("Signature", Security.getSignature(appid, secKey, nowDate, status));
                return R.ok().data(Map.of("Result", status));
            }
        }
        return R.error();
    }
}
