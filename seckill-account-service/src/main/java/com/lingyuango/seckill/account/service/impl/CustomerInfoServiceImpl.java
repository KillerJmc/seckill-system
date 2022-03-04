package com.lingyuango.seckill.account.service.impl;

import com.lingyuango.seckill.account.service.CustomerInfoService;
import com.lingyuango.seckill.account.dao.CustomerInfoDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class CustomerInfoServiceImpl implements CustomerInfoService {
    private final CustomerInfoDao customerInfoDao;
}
