package com.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.seckill.common.Const;
import com.seckill.dao.CustomerDao;
import com.seckill.pojo.Admin;
import com.seckill.pojo.Customer;
import com.seckill.service.CustomerService;
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
        var queryCustomer = customerDao.selectOne(
                Wrappers.<Customer>lambdaQuery()
                        .eq(Customer::getAccountId, customer.getAccountId())
                        .eq(Customer::getPassword, customer.getPassword())
        );
        return queryCustomer != null;
    }
}
