package com.seckill.controller;

import com.seckill.service.SeckillApplicationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class SeckillApplicationFormController {
    private final SeckillApplicationFormService seckillApplicationFormService;
}
