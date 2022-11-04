package com.lingyuango.seckill.account.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.account.client.MockCreditClient;
import com.lingyuango.seckill.account.dao.CustomerInfoDao;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.pojo.CustomerInfo;
import com.lingyuango.seckill.account.service.CustomerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class CustomerInfoServiceImpl implements CustomerInfoService {
    private final CustomerInfoDao customerInfoDao;
    private final MockCreditClient mockCreditClient;

    @Override
    public CustomerInfo getByAccount(Integer account) {
        return customerInfoDao.selectOne(
                Wrappers.<CustomerInfo>lambdaQuery()
                        .eq(CustomerInfo::getAccount, account)
        );
    }

    @Override
    public void insert(Customer customer) {
        // 获取模拟客户信息
        var mockCreditInfo = mockCreditClient.getCreditInfo().getData();

        customerInfoDao.insert(new CustomerInfo() {{
            setAccount(customer.getAccount());
            setWorkStatus(mockCreditInfo.getWorkStatus());
            setInCreditBlacklist(mockCreditInfo.getInCreditBlacklist());
            setOverdueTimes(mockCreditInfo.getOverdueTimes());
            setOverdueMoney(mockCreditInfo.getOverdueMoney());
            setOverdueDays(mockCreditInfo.getOverdueDays());
        }});
    }
}
