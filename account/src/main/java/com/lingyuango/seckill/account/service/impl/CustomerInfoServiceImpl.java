package com.lingyuango.seckill.account.service.impl;

import com.lingyuango.seckill.account.client.MockCreditClient;
import com.lingyuango.seckill.account.dao.CustomerInfoDao;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.pojo.CustomerInfo;
import com.lingyuango.seckill.account.service.CustomerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class CustomerInfoServiceImpl implements CustomerInfoService {
    private final CustomerInfoDao customerInfoDao;
    private final MockCreditClient mockCreditClient;

    @Override
    public CustomerInfo getByAccount(Integer account) {
        return customerInfoDao.getOneByAccount(account);
    }

    @Override
    public void insert(Customer customer) {
        // 获取模拟客户信息
        var mockCreditInfo = mockCreditClient.getCreditInfo().getData();

        var customerInfo = new CustomerInfo();
        customerInfo.setAccount(customer.getAccount());
        customerInfo.setWorkStatus(mockCreditInfo.getWorkStatus());
        customerInfo.setInCreditBlacklist(mockCreditInfo.getInCreditBlacklist());
        customerInfo.setOverdueTimes(mockCreditInfo.getOverdueTimes());
        customerInfo.setOverdueMoney(mockCreditInfo.getOverdueMoney());
        customerInfo.setOverdueDays(mockCreditInfo.getOverdueDays());

        customerInfoDao.save(customerInfo);
    }
}
