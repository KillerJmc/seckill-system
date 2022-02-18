package com.lingyuango.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.dao.SeckillApplicationFormDao;
import com.lingyuango.seckill.pojo.SeckillApplicationForm;
import com.lingyuango.seckill.service.CustomerService;
import com.lingyuango.seckill.service.SeckillActivityService;
import com.lingyuango.seckill.service.SeckillApplicationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class SeckillApplicationFormServiceImpl implements SeckillApplicationFormService {
    private final SeckillApplicationFormDao seckillApplicationFormDao;
    private final CustomerService customerService;
    private final SeckillActivityService seckillActivityService;

    @Override
    public boolean contains(int customerId) {
        return seckillApplicationFormDao.selectOne(
                Wrappers.<SeckillApplicationForm>lambdaQuery()
                        .eq(SeckillApplicationForm::getAccountId, customerId)
        ) != null;
    }

    @Override
    public boolean insert(int customerId) {
        var canApply = customerService.canApply(customerId);

        if (canApply) {
            var seckillId = seckillActivityService.getLatestSeckillId();
            var date = LocalDateTime.now();
            var seckillApplicationForm = new SeckillApplicationForm() {{
                setSeckillId(seckillId);
                setAccountId(customerId);
                setGmtCreate(date);
                setGmtModified(date);
            }};
            seckillApplicationFormDao.insert(seckillApplicationForm);
        }

        return canApply;
    }
}
