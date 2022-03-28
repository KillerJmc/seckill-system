package com.lingyuango.seckill.mock.service;

import com.lingyuango.seckill.mock.pojo.PayInfo;
import com.lingyuango.seckill.mock.pojo.Order;

public interface PayService {
    Order Pay(PayInfo pay);
}
