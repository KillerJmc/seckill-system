package com.seckill.service.impl;

import com.seckill.common.Const;
import com.seckill.dao.CustomerInfoDao;
import com.seckill.pojo.CustomerInfo;
import com.seckill.service.CustomerInfoService;
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
}
