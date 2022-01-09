package com.seckill.service.impl;

import com.seckill.dao.SeckillActivityDao;
import com.seckill.service.SeckillActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class SeckillActivityServiceImpl implements SeckillActivityService {
    private final SeckillActivityDao seckillActivityDao;
}
