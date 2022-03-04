package com.lingyuango.seckill.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.service.SeckillApplicationFormService;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class SeckillApplicationFormController {
    private final SeckillApplicationFormService seckillApplicationFormService;
}
