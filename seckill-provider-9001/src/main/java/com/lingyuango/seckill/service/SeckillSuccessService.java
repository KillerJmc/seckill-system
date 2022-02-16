package com.lingyuango.seckill.service;

import com.lingyuango.seckill.pojo.SeckillSuccess;

/**
 * @author Jmc
 */
public interface SeckillSuccessService {
    boolean contains(Integer seckillId, Integer customerId);

    SeckillSuccess insert(Integer seckillId, Integer customerId);
}
