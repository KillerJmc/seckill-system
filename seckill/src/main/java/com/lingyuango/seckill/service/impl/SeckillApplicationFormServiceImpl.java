package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.client.CustomerClient;
import com.lingyuango.seckill.dao.SeckillApplicationFormDao;
import com.lingyuango.seckill.pojo.SeckillApplicationForm;
import com.lingyuango.seckill.service.SeckillActivityService;
import com.lingyuango.seckill.service.SeckillApplicationFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class SeckillApplicationFormServiceImpl implements SeckillApplicationFormService {
    private final SeckillApplicationFormDao seckillApplicationFormDao;
    private final CustomerClient customerClient;
    private final SeckillActivityService seckillActivityService;

    @Override
    public boolean contains(int account) {
        return seckillApplicationFormDao.getOneByAccountId(account) != null;
    }

    @Override
    public boolean insert(int account) {
        var canApply = customerClient.canApply(account).getData();

        if (canApply) {
            var seckillId = seckillActivityService.getLatestSeckillId();
            var seckillApplicationForm = new SeckillApplicationForm();
            seckillApplicationForm.setSeckillId(seckillId);
            seckillApplicationForm.setAccountId(account);

            seckillApplicationFormDao.save(seckillApplicationForm);
        }

        return canApply;
    }
}
