package com.lingyuango.seckill.mock.service.Impl;

import com.lingyuango.seckill.mock.common.Const;
import com.lingyuango.seckill.mock.dao.AccountDao;
import com.lingyuango.seckill.mock.dao.CheckDao;
import com.lingyuango.seckill.mock.dao.MoneyDao;
import com.lingyuango.seckill.mock.pojo.MockAccount;
import com.lingyuango.seckill.mock.pojo.Money;
import com.lingyuango.seckill.mock.service.CheckService;
import com.lingyuango.seckill.mock.utils.RandomGeneratorTool;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * @author ChaconneLuo
 */
@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService {

    private final CheckDao checkDao;
    private final AccountDao accountDao;
    private final MoneyDao moneyDao;

    @Override
    public boolean checkAccount(MockAccount inf) {
        var count = checkDao.countByIdNumber(inf.getIdNumber());
        if (count != 0L) {
            return true;
        } else {
            return randomInsertAccount(inf);
        }
    }

    public Boolean randomInsertAccount(MockAccount inf) {
        if (RandomGeneratorTool.getRandomBoolean(0.8)) {
            var date = LocalDateTime.now();
            var maxId = accountDao.getMaxId();
            if (maxId == null) {
                maxId = -1;
            }
            var id = maxId;
            var account = new MockAccount();
            account.setIdNumber(inf.getIdNumber());
            account.setName(inf.getName());
            account.setAccountId(id + Const.ACCOUNT_ID_OFFSET + 1);
            account.setGmtCreate(date);
            account.setGmtModified(date);
            accountDao.save(account);

            var count = checkDao.count(Example.of(inf));
            if (count == 1L) {

                var money = new Money();
                money.setAccountId(accountDao.getAccountId(inf));
                money.setMoney(RandomGeneratorTool.getRandomMoney());
                money.setGmtCreate(date);
                money.setGmtModified(date);
                moneyDao.save(money);

            } else {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }
}
