package com.lingyuango.seckill.account.service.impl;

import com.lingyuango.seckill.account.client.SeckillActivityClient;
import com.lingyuango.seckill.account.dao.PreScreeningDao;
import com.lingyuango.seckill.account.pojo.PreScreening;
import com.lingyuango.seckill.account.service.CustomerService;
import com.lingyuango.seckill.account.service.PreScreeningService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PreScreeningServiceImpl implements PreScreeningService {
    private final PreScreeningDao preScreeningDao;
    private final CustomerService customerService;
    private final SeckillActivityClient seckillActivityClient;

    @Override
    public int insert(Integer customerId) {
        var customer = customerService.getByAccount(customerId);
        var canApply = customerService.canApply(customerId);

        int seckillId = seckillActivityClient.getSeckillId().getData();
        var date = LocalDateTime.now();

        var preliminaryScreening = new PreScreening() {{
            setSeckillId(seckillId);
            setAccount(customerId);
            setName(customer.getName());
            setPass(canApply);
            setGmtCreate(date);
            setGmtModified(date);
        }};

        log.info("初筛信息：{}", preliminaryScreening);
        return preScreeningDao.insert(preliminaryScreening);
    }
}
