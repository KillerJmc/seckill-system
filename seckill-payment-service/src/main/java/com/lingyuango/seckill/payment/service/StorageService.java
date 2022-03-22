package com.lingyuango.seckill.payment.service;

import com.jmc.net.R;

/**
 * @author Lingyuango
 */
public interface StorageService {
    R getStorage(Integer seckillId);
    Boolean decrease(Integer seckillId);
}
