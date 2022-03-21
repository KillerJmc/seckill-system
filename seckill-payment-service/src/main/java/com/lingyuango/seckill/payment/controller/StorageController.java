package com.lingyuango.seckill.payment.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;

    @PostMapping("/getStorage")
    @ResponseBody
    public R getStorage(Integer seckillId) {
        return storageService.getStorage(seckillId);
    }
}
