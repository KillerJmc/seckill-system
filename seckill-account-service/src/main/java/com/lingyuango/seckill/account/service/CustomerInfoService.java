package com.lingyuango.seckill.account.service;

import com.lingyuango.seckill.account.pojo.CustomerInfo;

/**
 * @author Jmc
 */
public interface CustomerInfoService {
    CustomerInfo getByAccountId(Integer accountId);
}
