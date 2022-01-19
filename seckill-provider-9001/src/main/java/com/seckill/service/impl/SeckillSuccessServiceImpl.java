package com.seckill.service.impl;

import com.seckill.dao.SeckillSuccessDao;
import com.seckill.pojo.Customer;
import com.seckill.service.SeckillSuccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class SeckillSuccessServiceImpl implements SeckillSuccessService {
    private final SeckillSuccessDao seckillSuccessDao;

    @Override
    public List<Customer> getList(Integer seckillId) {
        return null;
    }
}
