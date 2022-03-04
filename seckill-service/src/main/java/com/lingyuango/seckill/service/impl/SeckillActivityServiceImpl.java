package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.service.SeckillActivityService;
import com.lingyuango.seckill.dao.SeckillActivityDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class SeckillActivityServiceImpl implements SeckillActivityService {
    private final SeckillActivityDao seckillActivityDao;
}
