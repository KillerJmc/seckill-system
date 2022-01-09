package com.seckill.service.impl;

import com.seckill.dao.SeckillApplicationFormDao;
import com.seckill.service.SeckillApplicationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class SeckillApplicationFormServiceImpl implements SeckillApplicationFormService {
    private final SeckillApplicationFormDao seckillApplicationFormDao;
}
