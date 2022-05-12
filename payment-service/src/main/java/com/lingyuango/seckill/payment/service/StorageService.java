package com.lingyuango.seckill.payment.service;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.Storage;

/**
 * @author Lingyuango
 */
public interface StorageService {
    Storage getStorage(Integer seckillId);
    Boolean decrease(Integer seckillId);
}
