package com.lingyuango.seckill.account.service;

import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Jmc
 */
public interface PreScreeningService {
    @PostMapping("/insert")
    void insert(Integer customerId);
}
