package com.lingyuango.seckill.account.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.account.service.CustomerService;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
}
