package com.lingyuango.seckill.mock.service;

import com.lingyuango.seckill.mock.pojo.PayInformation;
import com.lingyuango.seckill.mock.pojo.Order;

public interface PayService {
    Order Pay(PayInformation pay);
}
