package com.lingyuango.seckill.mock.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.mock.pojo.CreditInformation;
import com.lingyuango.seckill.mock.service.CreditMockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequiredArgsConstructor
@Controller
public class CreditMockController {
    private final CreditMockService creditMockService;

    @RequestMapping("/getRandomCredit")
    @ResponseBody
    public R<Map<String, CreditInformation>> getCreditInformation() {
        return R.ok()
                .data(Map.of("randomCredit", creditMockService.getRandomCreditInformation()));
    }
}
