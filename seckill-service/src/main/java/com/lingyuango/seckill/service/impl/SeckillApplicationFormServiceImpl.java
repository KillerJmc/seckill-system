package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.service.SeckillApplicationFormService;
import com.lingyuango.seckill.dao.SeckillApplicationFormDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class SeckillApplicationFormServiceImpl implements SeckillApplicationFormService {
    private final SeckillApplicationFormDao seckillApplicationFormDao;
}
