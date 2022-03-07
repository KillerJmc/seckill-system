package com.lingyuango.seckill.mock.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.mock.common.Const;
import com.lingyuango.seckill.mock.dao.AccountDao;
import com.lingyuango.seckill.mock.dao.CheckDao;
import com.lingyuango.seckill.mock.dao.MoneyDao;
import com.lingyuango.seckill.mock.pojo.Account;
import com.lingyuango.seckill.mock.pojo.CheckAccount;
import com.lingyuango.seckill.mock.pojo.Money;
import com.lingyuango.seckill.mock.service.CheckService;
import com.lingyuango.seckill.mock.utils.RandomGeneratorTool;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


/**
 * @author ChaconneLuo
 */
@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private CheckDao checkDao;
    private AccountDao accountDao;
    private MoneyDao moneyDao;

    @Override
    public boolean checkAccount(CheckAccount Inf) {

        var count = checkDao.selectCount(Wrappers.lambdaQuery(Inf));

        if (count != 0L) {
            return true;
        } else {
            return randomInsertAccount(Inf);
        }
    }

    public Boolean randomInsertAccount(CheckAccount Inf) {
        if (RandomGeneratorTool.getRandomBoolean(0.8)) {
            var maxId = accountDao.getMaxId();
            var account = new Account() {{
                setIdNumber(Inf.getIdNumber());
                setName(Inf.getName());
                setAccountId(maxId + Const.ACCOUNT_ID_OFFSET + 1);
            }};
            accountDao.insert(account);

            var count = checkDao.selectCount(Wrappers.lambdaQuery(Inf));

            if (count != 0L) {

                moneyDao.insertMoney(new Money() {{
                    setAccountId(accountDao.getAccountId(Inf));
                    setMoney(RandomGeneratorTool.getRandomMoney());
                }});

            } else {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
