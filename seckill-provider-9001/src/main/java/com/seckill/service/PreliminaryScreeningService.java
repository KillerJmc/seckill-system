package com.seckill.service;

import com.seckill.pojo.PreliminaryScreening;

/**
 * @author Jmc
 */
public interface PreliminaryScreeningService {
    boolean contains(String customerName);

    int insert(PreliminaryScreening preliminaryScreening);
}
