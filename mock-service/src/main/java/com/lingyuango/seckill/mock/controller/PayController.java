package com.lingyuango.seckill.mock.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jmc.net.R;
import com.jmc.time.Time;
import com.lingyuango.seckill.mock.common.Const;
import com.lingyuango.seckill.mock.common.MsgMapping;
import com.lingyuango.seckill.mock.pojo.MockOrder;
import com.lingyuango.seckill.mock.pojo.MockPayInfo;
import com.lingyuango.seckill.mock.service.PayService;
import com.lingyuango.seckill.mock.service.SecretKeyService;
import com.lingyuango.seckill.mock.utils.CheckDateStamp;
import com.lingyuango.seckill.mock.utils.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Map;

import static com.lingyuango.seckill.mock.common.MsgMapping.VERITY_ERROR;

/**
 * @author ChaconneLuo
 */

@RestController
@RequiredArgsConstructor
public class PayController {

    private final SecretKeyService secretKeyService;
    private final PayService payService;

    @PostMapping("/pay")
    public R<Map<String, MockOrder>> pay(@RequestHeader("Appid") String appid,
                                         @RequestHeader("Date-Stamp") LocalDateTime date,
                                         @RequestHeader("Signature") String signature,
                                         @RequestBody MockPayInfo pay,
                                         HttpServletResponse resp) throws JsonProcessingException {

        var secKey = secretKeyService.getSecretKey(appid);
        if (secKey == null) {
            throw new RuntimeException("secKey in DB is null!");
        }
        if (Security.verify(appid, secKey, date, signature, pay)) {
            if (CheckDateStamp.CheckOverTime(date)) {

                var status = payService.Pay(pay);
                var nowDate = LocalDateTime.now();
                var result = Map.of("Result", status);

                resp.addHeader("Appid", appid);
                resp.addHeader("Date-Stamp", Time.of(nowDate).toString(Const.DATE_TIME_FORMAT));
                resp.addHeader("Signature", Security.getSignature(appid, secKey, nowDate, result));
                return R.ok().data(result);
            }
            return R.error().msg(MsgMapping.OVERTIME).build();
        }
        return R.error().msg(VERITY_ERROR).build();
    }
}
