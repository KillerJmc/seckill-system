package com.lingyuango.seckill.mock.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.mock.common.MsgMapping;
import com.lingyuango.seckill.mock.pojo.Account;
import com.lingyuango.seckill.mock.service.CheckService;
import com.lingyuango.seckill.mock.service.SecretKeyService;
import com.lingyuango.seckill.mock.utils.CheckDateStamp;
import com.lingyuango.seckill.mock.utils.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

import static com.lingyuango.seckill.mock.common.MsgMapping.VERITY_ERROR;

/**
 * @author ChaconneLuo
 */
@Controller
@RequiredArgsConstructor
public class CheckController {

    private final SecretKeyService secretKeyService;
    private final CheckService checkService;

    @PostMapping("/checkInformation")
    public R<Map<String,Boolean>> checkInformation(@RequestHeader("Appid") String appid,
                              @RequestHeader("Date-Stamp") LocalDateTime date,
                              @RequestHeader("Signature") String signature,
                              @RequestBody Account inf,
                              HttpServletResponse resp) {

        var secKey = secretKeyService.getSecretKey(appid);
        if (Security.verify(appid, secKey, date, signature, inf)) {

            if (CheckDateStamp.CheckOverTime(date)) {

                boolean exist = checkService.checkAccount(inf);
                var nowDate = LocalDateTime.now();

                resp.addHeader("Appid", appid);
                resp.addHeader("Date-Stamp", String.valueOf(nowDate.toEpochSecond(ZoneOffset.of("+8"))));
                resp.addHeader("Signature", Security.getSignature(appid, secKey, nowDate, exist));
                return R.ok()
                        .data(Map.of("Result", exist));

            } else {
                return R.error()
                        .msg(MsgMapping.OVERTIME)
                        .build();
            }
        }
        return R.error()
                .msg(VERITY_ERROR)
                .build();
    }

}
