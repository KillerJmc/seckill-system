package com.lingyuango.seckill.payment.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.payment.service.StorageService;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;
}
