package com.seckill.service.impl;

import com.seckill.common.Const;
import com.seckill.dao.CustomerDao;
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
    public int insert(Customer customer) {
        return -1;
    }

    @Override
    public boolean contains(Customer customer) {
        return false;
    }
}
