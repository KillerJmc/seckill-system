package com.lingyuango.seckill.service;

/**
 * @author Jmc
 */
public interface SeckillApplicationFormService {
    boolean contains(int account);

    boolean insert(int account) throws Exception;
}
