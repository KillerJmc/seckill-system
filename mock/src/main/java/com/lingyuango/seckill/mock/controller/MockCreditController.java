package com.lingyuango.seckill.mock.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.mock.pojo.MockCreditInfo;
import com.lingyuango.seckill.mock.service.CreditMockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequiredArgsConstructor
@Controller
@RequestMapping("/mock")
public class MockCreditController {
    private final CreditMockService creditMockService;

    @RequestMapping("/getCreditInfo")
    @ResponseBody
    public R<MockCreditInfo> getCreditInfo() {
        return R.ok(creditMockService.getRandomCreditInformation());
    }
}
