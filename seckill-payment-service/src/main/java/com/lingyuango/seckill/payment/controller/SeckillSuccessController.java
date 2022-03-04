package com.lingyuango.seckill.payment.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.payment.service.SeckillSuccessService;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class SeckillSuccessController {
    private final SeckillSuccessService seckillSuccessService;
}
