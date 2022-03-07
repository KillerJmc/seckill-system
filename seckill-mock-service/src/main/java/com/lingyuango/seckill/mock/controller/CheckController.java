package com.lingyuango.seckill.mock.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.mock.common.Const;
import com.lingyuango.seckill.mock.common.MsgMapping;
import com.lingyuango.seckill.mock.pojo.CheckAccount;
import com.lingyuango.seckill.mock.pojo.CheckAccountReturn;
import com.lingyuango.seckill.mock.service.CheckService;
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
public class CheckController {

    private SecretKeyService secretKeyService;
    private CheckService checkService;

    @PostMapping("/CheckInformation")
    @ResponseBody
    public R CheckInformation(@RequestHeader("Appid") String appid,
                              @RequestHeader("Date-Stamp") LocalDateTime date,
                              @RequestHeader("Signature") String signature,
                              @RequestBody CheckAccount inf,
                              HttpServletResponse resp) {
        var secKey = secretKeyService.getSecretKey(appid);
        if (Security.verify(appid, secKey, date, signature, inf)) {
            if (CheckDateStamp.CheckOverTime(date)) {
                boolean exist = checkService.checkAccount(inf);
                var result = new CheckAccountReturn() {{
                    setAccountExist(exist);
                }};
                var nowDate = LocalDateTime.now();
                resp.addHeader("Appid", appid);
                resp.addHeader("Date-Stamp", String.valueOf(nowDate.toEpochSecond(ZoneOffset.of("+8"))));
                resp.addHeader("Signature", Security.getSignature(appid, secKey, nowDate, result));
                return R.ok().data(Map.of("Result", result));
            } else {
                return R.error().data(MsgMapping.OVERTIME);
            }
        }
        return R.error();
    }

}
