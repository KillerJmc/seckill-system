package com.lingyuango.seckill.account.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.account.service.CustomerInfoService;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class CustomerInfoController {
    private final CustomerInfoService customerInfoService;
}
