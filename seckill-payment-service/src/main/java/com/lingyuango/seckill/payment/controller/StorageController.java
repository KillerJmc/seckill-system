package com.lingyuango.seckill.payment.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.Storage;
import com.lingyuango.seckill.payment.service.StorageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/storage")
public class StorageController {
    private final StorageService storageService;

    @PostMapping("/get")
    @ResponseBody
    public R<Integer> get(Integer seckillId) {
        return R.ok()
                .data(storageService.getStorage(seckillId).getAmount());
    }
}
