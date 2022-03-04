package com.lingyuango.seckill.payment.service.impl;

import com.lingyuango.seckill.payment.service.SeckillSuccessService;
import com.lingyuango.seckill.payment.dao.SeckillSuccessDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class SeckillSuccessServiceImpl implements SeckillSuccessService {
    private final SeckillSuccessDao seckillSuccessDao;
}
