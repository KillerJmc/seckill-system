package com.seckill.service.impl;

import com.seckill.dao.PreliminaryScreeningDao;
import com.seckill.pojo.PreliminaryScreening;
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

    @Override
    public boolean contains(String customerName) {
        return false;
    }

    @Override
    public int insert(PreliminaryScreening preliminaryScreening) {
        return 0;
    }
}
