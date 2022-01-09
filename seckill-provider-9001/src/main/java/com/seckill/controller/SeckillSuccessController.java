package com.seckill.controller;

import com.seckill.service.SeckillSuccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class SeckillSuccessController {
    private final SeckillSuccessService seckillSuccessService;
}
