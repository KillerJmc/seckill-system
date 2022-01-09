package com.seckill.service.impl;

import com.seckill.dao.CustomerDao;
import com.seckill.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;
}
