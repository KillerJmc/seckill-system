package com.lingyuango.seckill.service;

import com.lingyuango.seckill.pojo.CustomerInfo;

/**
 * @author Jmc
 */
public interface CustomerInfoService {
    CustomerInfo getByAccountId(Integer accountId);
}
