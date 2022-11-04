package com.lingyuango.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jmc.lang.Tries;
import com.lingyuango.seckill.client.CustomerClient;
import com.lingyuango.seckill.dao.SeckillApplicationFormDao;
import com.lingyuango.seckill.pojo.SeckillApplicationForm;
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
    private final CustomerClient customerClient;
    private final SeckillActivityService seckillActivityService;

    @Override
    public boolean contains(int account) {
        return seckillApplicationFormDao.selectOne(
                Wrappers.<SeckillApplicationForm>lambdaQuery()
                        .eq(SeckillApplicationForm::getAccountId, account)
        ) != null;
    }

    @Override
    public boolean insert(int account) {
        var canApply = Tries.tryReturnsT(customerClient.canApply(account)::getData);

        if (canApply) {
            var seckillId = seckillActivityService.getLatestSeckillId();
            var date = LocalDateTime.now();
            var seckillApplicationForm = new SeckillApplicationForm() {{
                setSeckillId(seckillId);
                setAccountId(account);
                setGmtCreate(date);
                setGmtModified(date);
            }};
            seckillApplicationFormDao.insert(seckillApplicationForm);
        }

        return canApply;
    }
}
