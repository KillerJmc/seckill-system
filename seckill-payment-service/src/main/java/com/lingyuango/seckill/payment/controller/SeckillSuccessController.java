package com.lingyuango.seckill.payment.controller;

import com.jmc.net.R;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/seckillSuccess")
public class SeckillSuccessController {
    /**
     * 判断某客户是否在秒杀成功表里
     */
    @PostMapping("/contains")
    R<Boolean> contains(Integer seckillId, Integer customerId) {

    }
}
