package com.lingyuango.seckill.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.service.SeckillApplicationFormService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/seckillApplicationForm")
@Slf4j
public class SeckillApplicationFormController {
    private final SeckillApplicationFormService seckillApplicationFormService;

    /**
     * 判断某客户是否在申请表中
     */
    @PostMapping("/contains")
    public R<Boolean> contains(Integer customerId) {
        return R.ok()
                .data(seckillApplicationFormService.contains(customerId));
    }
}
