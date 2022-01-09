package com.seckill.controller;

import com.seckill.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;
}
