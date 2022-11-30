package com.lingyuango.seckill.mock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmc.net.R;
import com.jmc.time.Time;
import com.lingyuango.seckill.mock.common.Const;
import com.lingyuango.seckill.mock.common.MsgMapping;
import com.lingyuango.seckill.mock.pojo.MockAccount;
import com.lingyuango.seckill.mock.service.CheckService;
import com.lingyuango.seckill.mock.service.SecretKeyService;
import com.lingyuango.seckill.mock.utils.CheckDateStamp;
import com.lingyuango.seckill.mock.utils.Security;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;

import static com.lingyuango.seckill.mock.common.MsgMapping.VERITY_ERROR;

/**
 * @author ChaconneLuo
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CheckController {

    private final SecretKeyService secretKeyService;
    private final CheckService checkService;

    @PostMapping("/checkInformation")
    public R<Map<String, Boolean>> checkInformation(@RequestHeader("Appid") String appid,
                                                    @RequestHeader("Date-Stamp") LocalDateTime date,
                                                    @RequestHeader("Signature") String signature,
                                                    @RequestBody MockAccount inf,
                                                    HttpServletResponse resp) throws JsonProcessingException {
        var secKey = secretKeyService.getSecretKey(appid);
        if (Security.verify(appid, secKey, date, signature, inf)) {

            if (CheckDateStamp.CheckOverTime(date)) {

                boolean exist = checkService.checkAccount(inf);
                System.out.println("Insert Account:" + exist);
                var nowDate = LocalDateTime.now();
                Map<String, Boolean> result = Map.of("Result", exist);

                resp.addHeader("Appid", appid);
                resp.addHeader("Date-Stamp", Time.of(nowDate).toString(Const.DATE_TIME_FORMAT));
                resp.addHeader("Signature", Security.getSignature(appid, secKey, nowDate, result));
                return R.ok(result);

            } else {
                return R.error(MsgMapping.OVERTIME);
            }
        }
        return R.error(VERITY_ERROR);
    }

}
