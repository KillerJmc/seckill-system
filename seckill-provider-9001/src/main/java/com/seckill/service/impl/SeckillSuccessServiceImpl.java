package com.seckill.service.impl;

import com.seckill.dao.SeckillSuccessDao;
import com.seckill.service.SeckillSuccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class SeckillSuccessServiceImpl implements SeckillSuccessService {
    private final SeckillSuccessDao seckillSuccessDao;
}
