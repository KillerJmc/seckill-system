package com.seckill.controller;

import com.seckill.service.CustomerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class CustomerInfoController {
    private final CustomerInfoService customerInfoService;
}
