package com.lingyuango.seckill.mock.service.Impl;

import com.lingyuango.seckill.mock.pojo.CreditInformation;
import com.lingyuango.seckill.mock.service.CreditMockService;
import com.lingyuango.seckill.mock.utils.RandomGeneratorTool;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditMockServiceImpl implements CreditMockService {

    @Override
    public CreditInformation getRandomCreditInformation() {
        return new CreditInformation() {{
            setWorkStatus(RandomGeneratorTool.getRandomBoolean(0.8));
            setAge(RandomGeneratorTool.getRandomInteger(13, 85, 0.1, 18));
            setInCreditBlacklist(RandomGeneratorTool.getRandomBoolean(0.1));
            setOverdueTimes(RandomGeneratorTool.getRandomInteger(0, 10, 0.95, 1));
            setOverdueDays(RandomGeneratorTool.getRandomInteger(0, 50, 0.95, 1));
            setOverdueMoney(RandomGeneratorTool.getRandomInteger(0, 10000, 0.95, 1));
        }};
    }

}
