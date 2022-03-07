package com.lingyuango.seckill.mock.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.mock.common.Const;
import com.lingyuango.seckill.mock.dao.MoneyDao;
import com.lingyuango.seckill.mock.dao.OrderDao;
import com.lingyuango.seckill.mock.pojo.Money;
import com.lingyuango.seckill.mock.pojo.PayInformation;
import com.lingyuango.seckill.mock.pojo.Order;
import com.lingyuango.seckill.mock.service.PayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author ChaconneLuo
 */
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private MoneyDao moneyDao;
    private OrderDao orderDao;

    @Override
    public Order Pay(PayInformation pay) {
        boolean paySuccess;
        var payMoney = pay.getMoney();
        var accountId = moneyDao.getAccountId(pay);
        var money = moneyDao.selectOne(Wrappers.<Money>lambdaQuery().eq(Money::getAccountId, accountId)).getMoney();

        if (money < payMoney) {
            paySuccess = false;
        } else {
            moneyDao.updateMoney(accountId, money - payMoney);
            paySuccess = true;
        }

        var order = new Order() {{
            setAccountId(accountId);
            setMoney(-payMoney);
            setPaySuccess(paySuccess);
            setOrderId(orderDao.getMaxId() + Const.ORDER_ID_OFFSET + 1);
        }};

        orderDao.insert(order);
        return order;
    }
}
