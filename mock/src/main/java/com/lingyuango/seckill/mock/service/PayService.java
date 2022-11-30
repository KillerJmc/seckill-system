package com.lingyuango.seckill.mock.service;

import com.lingyuango.seckill.mock.pojo.MockOrder;
import com.lingyuango.seckill.mock.pojo.MockPayInfo;

public interface PayService {
    MockOrder Pay(MockPayInfo pay);
}
