package com.seckill.service.impl;

import com.seckill.dao.SeckillActivityRuleDao;
import com.seckill.pojo.SeckillActivityRule;
import com.seckill.service.SeckillActivityRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class SeckillActivityRuleServiceImpl implements SeckillActivityRuleService {
    private final SeckillActivityRuleDao seckillActivityRuleDao;

    @Override
    public SeckillActivityRule getById(Integer id) {
        return seckillActivityRuleDao.selectById(id);
    }
}
