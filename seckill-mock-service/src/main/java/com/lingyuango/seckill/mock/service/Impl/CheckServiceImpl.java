package com.lingyuango.seckill.mock.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.mock.common.Const;
import com.lingyuango.seckill.mock.dao.AccountDao;
import com.lingyuango.seckill.mock.dao.CheckDao;
import com.lingyuango.seckill.mock.dao.MoneyDao;
import com.lingyuango.seckill.mock.pojo.MockAccount;
import com.lingyuango.seckill.mock.pojo.Money;
import com.lingyuango.seckill.mock.service.CheckService;
import com.lingyuango.seckill.mock.utils.RandomGeneratorTool;
import lombok.RequiredArgsConstructor;

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

        var count = checkDao.selectCount(Wrappers.<MockAccount>lambdaQuery()
                .eq(MockAccount::getIdNumber, inf.getIdNumber()));

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
            var account = new MockAccount() {{
                setIdNumber(inf.getIdNumber());
                setName(inf.getName());
                setAccountId(id + Const.ACCOUNT_ID_OFFSET + 1);
            }};
            account.setGmtCreate(date);
            account.setGmtModified(date);
            accountDao.insert(account);

            var count = checkDao.selectCount(Wrappers.lambdaQuery(inf));
            if (count == 1L) {

                moneyDao.insert(new Money() {{
                    setAccountId(accountDao.getAccountId(inf));
                    setMoney(RandomGeneratorTool.getRandomMoney());
                    setGmtCreate(date);
                    setGmtModified(date);
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
