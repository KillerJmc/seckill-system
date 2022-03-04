package com.lingyuango.seckill.account.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.account.service.PreScreeningService;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class PreScreeningController {
    private final PreScreeningService preScreeningService;
}
