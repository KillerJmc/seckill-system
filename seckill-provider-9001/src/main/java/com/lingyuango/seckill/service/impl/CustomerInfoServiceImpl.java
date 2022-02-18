package com.lingyuango.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.dao.CustomerInfoDao;
import com.lingyuango.seckill.pojo.CustomerInfo;
import com.lingyuango.seckill.service.CustomerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class CustomerInfoServiceImpl implements CustomerInfoService {
    private final CustomerInfoDao customerInfoDao;

    @Override
    public CustomerInfo getByAccountId(Integer accountId) {
        return customerInfoDao.selectOne(Wrappers.<CustomerInfo>lambdaQuery().eq(CustomerInfo::getAccountId, accountId));
    }
}
