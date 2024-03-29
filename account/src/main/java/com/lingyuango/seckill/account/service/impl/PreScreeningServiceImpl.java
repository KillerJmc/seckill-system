package com.lingyuango.seckill.account.service.impl;

import com.lingyuango.seckill.account.client.SeckillActivityClient;
import com.lingyuango.seckill.account.dao.PreScreeningDao;
import com.lingyuango.seckill.account.pojo.PreScreening;
import com.lingyuango.seckill.account.service.CustomerService;
import com.lingyuango.seckill.account.service.PreScreeningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
@Slf4j
public class PreScreeningServiceImpl implements PreScreeningService {
    private final PreScreeningDao preScreeningDao;
    private final CustomerService customerService;
    private final SeckillActivityClient seckillActivityClient;

    @Override
    public void insert(Integer customerId) {
        var customer = customerService.getByAccount(customerId);
        var canApply = customerService.canApply(customerId);

        int seckillId = seckillActivityClient.getSeckillId().getData();

        var preScreen = new PreScreening();
        preScreen.setSeckillId(seckillId);
        preScreen.setAccount(customerId);
        preScreen.setName(customer.getName());
        preScreen.setPass(canApply);

        log.info("初筛信息：{}", preScreen);
        preScreeningDao.save(preScreen);
    }
}
