package com.lingyuango.seckill.mock.service;

import com.lingyuango.seckill.mock.pojo.MockPayInfo;
import com.lingyuango.seckill.mock.pojo.MockOrder;

public interface PayService {
    MockOrder Pay(MockPayInfo pay);
}
