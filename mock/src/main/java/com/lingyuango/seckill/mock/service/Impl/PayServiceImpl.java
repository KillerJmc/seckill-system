package com.lingyuango.seckill.mock.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.mock.common.Const;
import com.lingyuango.seckill.mock.dao.MoneyDao;
import com.lingyuango.seckill.mock.dao.OrderDao;
import com.lingyuango.seckill.mock.pojo.MockOrder;
import com.lingyuango.seckill.mock.pojo.MockPayInfo;
import com.lingyuango.seckill.mock.pojo.Money;
import com.lingyuango.seckill.mock.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author ChaconneLuo
 */
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final MoneyDao moneyDao;
    private final OrderDao orderDao;

    @Override
    public MockOrder Pay(MockPayInfo pay) {
        boolean paySuccess;
        var payMoney = pay.getMoney();
        var accountId = moneyDao.getAccountId(pay);
        var money = moneyDao.selectOne(Wrappers.<Money>lambdaQuery().eq(Money::getAccountId, accountId)).getMoney();
        var date = LocalDateTime.now();

        if (money < payMoney) {
            paySuccess = false;
        } else {
            moneyDao.update(new Money() {{
                setAccountId(accountId);
                setGmtModified(LocalDateTime.now());
                setMoney(money - payMoney);
            }}, Wrappers.<Money>update().eq("account_id", accountId));
            paySuccess = true;
        }

        var maxId = orderDao.getMaxId();
        if (maxId == null) {
            maxId = -1;
        }
        var id = maxId;
        var order = new MockOrder() {{
            setAccountId(accountId);
            setMoney(-payMoney);
            setPaySuccess(paySuccess);
            setOrderId(id + Const.ORDER_ID_OFFSET + 1);
            setGmtCreate(date);
            setGmtModified(date);
        }};
        orderDao.insert(order);
        return order;
    }
}
