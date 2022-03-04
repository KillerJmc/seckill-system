package com.lingyuango.seckill.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.service.SeckillActivityService;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class SeckillActivityController {
    private final SeckillActivityService seckillActivityService;
}
