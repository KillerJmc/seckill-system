package com.lingyuango.seckill.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.service.SeckillActivityRuleService;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class SeckillActivityRuleController {
    private final SeckillActivityRuleService seckillActivityRuleService;
}
