package com.lingyuango.seckill.payment.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jmc.net.R;
import com.lingyuango.seckill.payment.client.CustomerClient;
import com.lingyuango.seckill.payment.client.PayClient;
import com.lingyuango.seckill.payment.common.Const;
import com.lingyuango.seckill.payment.common.MsgMapping;
import com.lingyuango.seckill.payment.dao.OrderDao;
import com.lingyuango.seckill.payment.pojo.*;
import com.lingyuango.seckill.payment.service.OrderService;
import com.lingyuango.seckill.payment.service.PayService;
import com.lingyuango.seckill.payment.service.RedisService;
import com.lingyuango.seckill.payment.service.StorageService;
import com.lingyuango.seckill.payment.utils.Security;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author ChaconneLuo
 */

@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final OrderDao orderDao;
    private final OrderService orderService;
    private final StorageService storageService;
    private final CustomerClient customerClient;
    private final PayClient payClient;
    private final RedisService redisService;

    @Override
    @Transactional
    public synchronized R<PaymentStatus> pay(String orderId) throws IOException {
        var order = orderDao.selectOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderId, orderId));
        var customer = customerClient.getByAccount(order.getAccountId()).getData();
        var product = redisService.getActivityProduct(order.getSeckillId());

        var date = LocalDateTime.now();
        var checkAccount = new MockAccount() {{
            setName(customer.getName());
            setIdNumber(customer.getIdNumber());
        }};
        var checkSignature = Security.getSignature(Const.Appid, Const.secKey, date, checkAccount);
        var checkResponse = payClient.checkInformation(Const.Appid, date, checkSignature, checkAccount);

        Boolean isSuccess = Security.VerifyMapMessage(checkResponse, Boolean.class);
        if (Boolean.TRUE.equals(isSuccess)) {
            var payInfo = new MockPayInfo() {{
                setMoney(product.getPrice());
                setIdNumber(customer.getIdNumber());
                setName(customer.getName());
            }};
            var signature = Security.getSignature(Const.Appid, Const.secKey, date, payInfo);
            var response = payClient.pay(Const.Appid, date, signature, payInfo);
            var payOrder = Security.VerifyMapMessage(response, MockOrder.class);
            if (payOrder != null) {
                if (payOrder.getPaySuccess()) {
                    var flag = orderService.update(orderId);
                    var storageFlag = storageService.decrease(order.getSeckillId());
                    if (flag && storageFlag) {
                        return R.ok(new PaymentStatus() {{
                            setAccountId(customer.getAccount());
                            setOrderId(orderId);
                            setPaymentSuccess(true);
                        }});
                    } else {
                        return R.error(MsgMapping.UNKNOWN_ERROR);
                    }
                } else {
                    return R.error(MsgMapping.INSUFFICIENT_BALANCE);
                }
            }
        }
        return R.error(MsgMapping.ACCOUNT_NOT_EXISTED);
    }
}
