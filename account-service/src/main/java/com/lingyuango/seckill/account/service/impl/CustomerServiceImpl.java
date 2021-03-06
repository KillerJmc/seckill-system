package com.lingyuango.seckill.account.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.account.common.Const;
import com.lingyuango.seckill.account.dao.CustomerDao;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.service.CustomerInfoService;
import com.lingyuango.seckill.account.service.CustomerService;
import com.lingyuango.seckill.account.client.SeckillActivityClient;
import com.lingyuango.seckill.account.util.Calculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;
    private final SeckillActivityClient seckillActivityClient;
    private final CustomerInfoService customerInfoService;

    @Override
    public boolean insert(Customer customer) {
        var queryCustomer = customerDao.selectCount(
                Wrappers.<Customer>lambdaQuery()
                        .eq(Customer::getIdNumber, customer.getIdNumber())
        );

        if (queryCustomer == 1) {
            return false;
        }

        var maxId = customerDao.getMaxId();
        if (maxId == null) {
            maxId = 0;
        }

        var now = LocalDateTime.now();
        customer.setAccountId(Const.ACCOUNT_ID_OFFSET + maxId + 1);
        customer.setGmtCreate(now);
        customer.setGmtModified(now);

        return customerDao.insert(customer) == 1;
    }

    @Override
    public boolean contains(Customer customer) {
        var queryCustomer = customerDao.selectOne(Wrappers.query(customer));

        if (queryCustomer != null) {
            // 回填信息
            customer.setAccountId(queryCustomer.getAccountId());
            customer.setName(queryCustomer.getName());
        }

        return queryCustomer != null;
    }

    @Override
    public Customer getByAccountId(Integer accountId) {
        return customerDao.selectOne(Wrappers.<Customer>lambdaQuery().eq(Customer::getAccountId, accountId));
    }

    @Override
    public boolean canApply(Integer accountId) {
        var customer = getByAccountId(accountId);

        var rule = seckillActivityClient.getRule().get();
        var customerInfo = customerInfoService.getByAccountId(accountId);
        int customerAge = Calculator.getAge(customer.getIdNumber());

        return  customerAge >= rule.getMinAge() && customerAge <= rule.getMaxAge() &&
                customerInfo.getWorkStatus() == rule.getWorkStatus() &&
                customerInfo.getInCreditBlacklist() == rule.getInCreditBlacklist() &&
                customerInfo.getOverdueTimes() <= rule.getMaxOverdueTimes() &&
                customerInfo.getOverdueDays() <= rule.getMaxOverdueDays() &&
                customerInfo.getOverdueMoney() <= rule.getMaxOverdueMoney();
    }
}
