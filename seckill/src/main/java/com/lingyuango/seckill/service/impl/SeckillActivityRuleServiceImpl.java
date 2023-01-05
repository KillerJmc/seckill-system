package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.dao.SeckillActivityRuleDao;
import com.lingyuango.seckill.pojo.SeckillActivityRule;
import com.lingyuango.seckill.service.SeckillActivityRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class SeckillActivityRuleServiceImpl implements SeckillActivityRuleService {
    private final SeckillActivityRuleDao seckillActivityRuleDao;

    @Override
    public SeckillActivityRule getById(Long id) {
        return seckillActivityRuleDao.findById(id).orElseThrow();
    }
}
