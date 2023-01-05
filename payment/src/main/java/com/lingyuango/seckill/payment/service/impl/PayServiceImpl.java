package com.lingyuango.seckill.payment.service.impl;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.client.CustomerClient;
import com.lingyuango.seckill.payment.client.PayClient;
import com.lingyuango.seckill.payment.common.Const;
import com.lingyuango.seckill.payment.common.MsgMapping;
import com.lingyuango.seckill.payment.dao.OrderDao;
import com.lingyuango.seckill.payment.pojo.MockAccount;
import com.lingyuango.seckill.payment.pojo.MockOrder;
import com.lingyuango.seckill.payment.pojo.MockPayInfo;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;
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
        var order = orderDao.getOneByOrderId(orderId);
        var customer = customerClient.getByAccount(order.getAccountId()).getData();
        var product = redisService.getActivityProduct(order.getSeckillId());

        var date = LocalDateTime.now();
        var checkAccount = new MockAccount();
        checkAccount.setName(customer.getName());
        checkAccount.setIdNumber(customer.getIdNumber());
        var checkSignature = Security.getSignature(Const.Appid, Const.secKey, date, checkAccount);
        var checkResponse = payClient.checkInformation(Const.Appid, date, checkSignature, checkAccount);

        Boolean isSuccess = Security.VerifyMapMessage(checkResponse, Boolean.class);
        if (Boolean.TRUE.equals(isSuccess)) {
            var payInfo = new MockPayInfo();
            payInfo.setMoney(product.getPrice());
            payInfo.setIdNumber(customer.getIdNumber());
            payInfo.setName(customer.getName());
            var signature = Security.getSignature(Const.Appid, Const.secKey, date, payInfo);
            var response = payClient.pay(Const.Appid, date, signature, payInfo);
            var payOrder = Security.VerifyMapMessage(response, MockOrder.class);
            if (payOrder != null) {
                if (payOrder.getPaySuccess()) {
                    orderService.update(orderId);
                    storageService.decrease(order.getSeckillId());
                    return R.ok(new PaymentStatus() {{
                        setAccountId(customer.getAccount());
                        setOrderId(orderId);
                        setPaymentSuccess(true);
                    }});
                } else {
                    return R.error(MsgMapping.INSUFFICIENT_BALANCE);
                }
            }
        }
        return R.error(MsgMapping.ACCOUNT_NOT_EXISTED);
    }
}
