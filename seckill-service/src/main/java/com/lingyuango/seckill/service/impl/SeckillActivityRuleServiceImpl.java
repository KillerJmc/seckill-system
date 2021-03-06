package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.pojo.SeckillActivityRule;
import com.lingyuango.seckill.service.SeckillActivityRuleService;
import com.lingyuango.seckill.dao.SeckillActivityRuleDao;
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
