package com.seckill.controller;

import com.seckill.service.SeckillActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class SeckillActivityController {
    private final SeckillActivityService seckillActivityService;
}
