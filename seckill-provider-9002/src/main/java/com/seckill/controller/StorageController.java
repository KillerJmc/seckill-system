package com.seckill.controller;

import com.seckill.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;
}
