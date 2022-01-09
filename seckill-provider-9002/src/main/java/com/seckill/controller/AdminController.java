package com.seckill.controller;

import com.seckill.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
}
