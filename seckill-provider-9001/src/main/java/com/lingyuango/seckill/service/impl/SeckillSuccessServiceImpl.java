package com.lingyuango.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.dao.SeckillSuccessDao;
import com.lingyuango.seckill.pojo.SeckillSuccess;
import com.lingyuango.seckill.service.SeckillActivityService;
import com.lingyuango.seckill.service.SeckillSuccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class SeckillSuccessServiceImpl implements SeckillSuccessService {
    private final SeckillSuccessDao seckillSuccessDao;
    private final SeckillActivityService seckillActivityService;

    @Override
    public boolean contains(Integer seckillId, Integer customerId) {
        return seckillSuccessDao.selectOne(
                Wrappers.<SeckillSuccess>lambdaQuery()
                        .eq(SeckillSuccess::getSeckillId, seckillId)
                        .eq(SeckillSuccess::getAccountId, customerId)
        ) != null;
    }

    @Override
    public SeckillSuccess insert(Integer seckillId, Integer customerId) {
        String orderId = UUID.randomUUID().toString();

        var date = LocalDateTime.now();
        var seckillSuccess = new SeckillSuccess() {{
            setSeckillId(seckillId);
            setAccountId(customerId);
            setOrderId(orderId);
            setGmtCreate(date);
            setGmtModified(date);
        }};

        var returnV = seckillSuccessDao.insert(seckillSuccess);
        if (returnV == 0) {
            return null;
        }

        seckillSuccess.setId(null);
        seckillSuccess.setSeckillId(null);
        // 每个客户只能订购一份，订单价格等于每份商品的价格
        var orderPrice = seckillActivityService.getLatest().getProduct().getPrice();
        seckillSuccess.setOrderPrice(orderPrice);
        return seckillSuccess;
    }
}
