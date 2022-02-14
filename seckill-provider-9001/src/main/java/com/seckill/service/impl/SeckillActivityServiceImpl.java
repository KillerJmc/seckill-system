package com.seckill.service.impl;

import com.seckill.dao.SeckillActivityDao;
import com.seckill.pojo.SeckillActivity;
import com.seckill.service.ProductService;
import com.seckill.service.SeckillActivityRuleService;
import com.seckill.service.SeckillActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class SeckillActivityServiceImpl implements SeckillActivityService {
    private final SeckillActivityDao seckillActivityDao;
    private final SeckillActivityRuleService seckillActivityRuleService;
    private final ProductService productService;

    @Override
    public synchronized SeckillActivity getLatest() {
        var res = seckillActivityDao.getLatest();


        if (res == null) {
            return null;
        }

        var activityRule = seckillActivityRuleService.getById(res.getActivityRuleId());
        activityRule.setId(null);
        res.setActivityRuleId(null);
        res.setActivityRule(activityRule);

        var product = productService.getByProductId(res.getProductId());
        product.setId(null);
        product.setProductId(null);
        res.setProductId(null);
        res.setProduct(product);

        return res;
    }

    @Override
    public int getLatestSeckillId() {
        return seckillActivityDao.getLatestSeckillId();
    }
}
