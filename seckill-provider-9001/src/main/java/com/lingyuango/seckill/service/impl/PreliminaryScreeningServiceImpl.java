package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.dao.PreliminaryScreeningDao;
import com.lingyuango.seckill.pojo.PreliminaryScreening;
import com.lingyuango.seckill.service.CustomerService;
import com.lingyuango.seckill.service.PreliminaryScreeningService;
import com.lingyuango.seckill.service.SeckillActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class PreliminaryScreeningServiceImpl implements PreliminaryScreeningService {
    private final PreliminaryScreeningDao preliminaryScreeningDao;
    private final CustomerService customerService;
    private final SeckillActivityService seckillActivityService;

    @Override
    public int insert(int customerId) {
        var customer = customerService.getByAccountId(customerId);
        var canApply = customerService.canApply(customerId);

        int seckillId = seckillActivityService.getLatestSeckillId();
        var date = LocalDateTime.now();

        var preliminaryScreening = new PreliminaryScreening() {{
            setSeckillId(seckillId);
            setAccountId(customerId);
            setName(customer.getName());
            setPass(canApply);
            setGmtCreate(date);
            setGmtModified(date);
        }};

        return preliminaryScreeningDao.insert(preliminaryScreening);
    }
}
