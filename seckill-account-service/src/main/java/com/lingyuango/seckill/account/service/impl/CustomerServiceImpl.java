package com.lingyuango.seckill.account.service.impl;

import com.lingyuango.seckill.account.service.CustomerService;
import com.lingyuango.seckill.account.dao.CustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;
}
