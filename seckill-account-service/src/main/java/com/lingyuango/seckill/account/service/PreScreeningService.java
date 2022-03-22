package com.lingyuango.seckill.account.service;

import com.jmc.net.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Jmc
 */
public interface PreScreeningService {
    @PostMapping("/insert")
    int insert(Integer customerId);
}
