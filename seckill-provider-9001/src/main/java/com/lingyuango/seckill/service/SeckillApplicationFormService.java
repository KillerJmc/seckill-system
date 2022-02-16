package com.lingyuango.seckill.service;

/**
 * @author Jmc
 */
public interface SeckillApplicationFormService {
    boolean contains(int customerId);

    boolean insert(int customerId);
}
