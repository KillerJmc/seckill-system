package com.seckill.service.impl;

import com.seckill.dao.AdminDao;
import com.seckill.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;
}
