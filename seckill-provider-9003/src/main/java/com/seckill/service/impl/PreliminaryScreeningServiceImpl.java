package com.seckill.service.impl;

import com.seckill.dao.PreliminaryScreeningDao;
import com.seckill.service.PreliminaryScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class PreliminaryScreeningServiceImpl implements PreliminaryScreeningService {
    private final PreliminaryScreeningDao preliminaryScreeningDao;
}
