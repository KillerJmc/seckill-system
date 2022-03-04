package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.service.SeckillActivityRuleService;
import com.lingyuango.seckill.dao.SeckillActivityRuleDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class SeckillActivityRuleServiceImpl implements SeckillActivityRuleService {
    private final SeckillActivityRuleDao seckillActivityRuleDao;
}
