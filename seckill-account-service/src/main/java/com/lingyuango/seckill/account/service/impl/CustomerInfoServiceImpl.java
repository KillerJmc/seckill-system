package com.lingyuango.seckill.account.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.pojo.CustomerInfo;
import com.lingyuango.seckill.account.pojo.MockCreditInfo;
import com.lingyuango.seckill.account.service.CustomerInfoService;
import com.lingyuango.seckill.account.dao.CustomerInfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Override
    public boolean insert(Customer customer, MockCreditInfo mockCreditInfo) {
        var date = LocalDateTime.now();
        return customerInfoDao.insert(new CustomerInfo() {{
            setAccountId(customer.getAccountId());
            setWorkStatus(mockCreditInfo.getWorkStatus());
            setInCreditBlacklist(mockCreditInfo.getInCreditBlacklist());
            setOverdueTimes(mockCreditInfo.getOverdueTimes());
            setOverdueMoney(mockCreditInfo.getOverdueMoney());
            setOverdueDays(mockCreditInfo.getOverdueDays());
            setGmtCreate(date);
            setGmtModified(date);
        }}) == 1;
    }
}
