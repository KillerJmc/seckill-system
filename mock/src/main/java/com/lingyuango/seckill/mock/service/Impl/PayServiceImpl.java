package com.lingyuango.seckill.mock.service.Impl;

import com.lingyuango.seckill.mock.common.Const;
import com.lingyuango.seckill.mock.dao.MoneyDao;
import com.lingyuango.seckill.mock.dao.OrderDao;
import com.lingyuango.seckill.mock.pojo.MockOrder;
import com.lingyuango.seckill.mock.pojo.MockPayInfo;
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
        var money = moneyDao.getOneByAccountIdIs(accountId).getMoney();
        var date = LocalDateTime.now();

        if (money < payMoney) {
            paySuccess = false;
        } else {
            var moneyObj = moneyDao.getOneByAccountIdIs(accountId);
            moneyObj.setGmtModified(date);
            moneyObj.setMoney(money - payMoney);
            moneyDao.save(moneyObj);
            paySuccess = true;
        }

        var maxId = orderDao.getMaxId();
        if (maxId == null) {
            maxId = -1;
        }
        var id = maxId;
        var order = new MockOrder();
        order.setAccountId(accountId);
        order.setMoney(-payMoney);
        order.setPaySuccess(paySuccess);
        order.setOrderId(id + Const.ORDER_ID_OFFSET + 1);
        order.setGmtCreate(date);
        order.setGmtModified(date);
        orderDao.save(order);
        return order;
    }
}
