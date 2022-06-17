package com.lingyuango.seckill.mock.service.Impl;

import com.lingyuango.seckill.mock.pojo.MockCreditInfo;
import com.lingyuango.seckill.mock.service.CreditMockService;
import com.lingyuango.seckill.mock.utils.RandomGeneratorTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditMockServiceImpl implements CreditMockService {

    @Override
    public MockCreditInfo getRandomCreditInformation() {
        return new MockCreditInfo() {{
            setWorkStatus(RandomGeneratorTool.getRandomBoolean(0.99));
            setAge(RandomGeneratorTool.getRandomInteger(13, 35, 0.01, 18));
            setInCreditBlacklist(RandomGeneratorTool.getRandomBoolean(0.01));
            setOverdueTimes(RandomGeneratorTool.getRandomInteger(0, 10, 0.99, 1));
            setOverdueDays(RandomGeneratorTool.getRandomInteger(0, 50, 0.99, 1));
            setOverdueMoney((double) RandomGeneratorTool.getRandomInteger(0, 10000, 0.99, 1));
        }};
    }

}
