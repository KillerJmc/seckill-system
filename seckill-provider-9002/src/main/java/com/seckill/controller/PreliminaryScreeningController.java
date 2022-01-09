package com.seckill.controller;

import com.seckill.service.PreliminaryScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class PreliminaryScreeningController {
    private final PreliminaryScreeningService preliminaryScreeningService;
}
