package com.seckill.service.impl;

import com.seckill.dao.CustomerInfoDao;
import com.seckill.service.CustomerInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class CustomerInfoServiceImpl implements CustomerInfoService {
    private final CustomerInfoDao customerInfoDao;
}
