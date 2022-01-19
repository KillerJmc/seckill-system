package com.seckill.service.impl;

import com.seckill.dao.SeckillActivityDao;
import com.seckill.pojo.SeckillActivity;
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

    @Override
    public void insert(SeckillActivity seckillActivity) {

    }

    @Override
    public SeckillActivity getOne() {
        return null;
    }
}
