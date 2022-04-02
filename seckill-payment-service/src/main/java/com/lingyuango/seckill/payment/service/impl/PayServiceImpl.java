package com.lingyuango.seckill.payment.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmc.net.HttpStatus;
import com.jmc.net.R;
import com.lingyuango.seckill.payment.common.Const;
import com.lingyuango.seckill.payment.common.MsgMapping;
import com.lingyuango.seckill.payment.dao.OrderDao;
import com.lingyuango.seckill.payment.pojo.*;
import com.lingyuango.seckill.payment.client.CustomerClient;
import com.lingyuango.seckill.payment.client.SeckillActivityClient;
import com.lingyuango.seckill.payment.client.PayClient;
import com.lingyuango.seckill.payment.service.OrderService;
import com.lingyuango.seckill.payment.service.PayService;
import com.lingyuango.seckill.payment.service.StorageService;
import com.lingyuango.seckill.payment.utils.CheckDateStamp;
import com.lingyuango.seckill.payment.utils.Security;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

/**
 * @author ChaconneLuo
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final OrderDao orderDao;
    private final OrderService orderService;
    private final StorageService storageService;
    private final CustomerClient customerClient;
    private final SeckillActivityClient seckillActivityClient;
    private final PayClient payClient;

    @Override
    @Transactional
    public synchronized R<PaymentStatus> pay(String orderId) throws IOException {
        var order = orderDao.selectOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderId, orderId));
        var customer = customerClient.getCustomer(order.getAccountId()).getData();
        var product = seckillActivityClient.getProduct(order.getSeckillId());

        var date = LocalDateTime.now();
        var checkAccount = new MockAccount() {{
            setName(customer.getName());
            setIdNumber(customer.getIdNumber());
        }};
        var checkSignature = Security.getSignature(Const.Appid, Const.secKey, date, checkAccount);
        var checkResponse = payClient.checkInformation(Const.Appid, date, checkSignature, checkAccount);

        Boolean isSuccess = Security.VerifyMapMessage(checkResponse, Boolean.class);
        log.info(isSuccess + "");
        if (Boolean.TRUE.equals(isSuccess)) {
            var payInfo = new MockPayInfo() {{
                setMoney(product.getPrice());
                setIdNumber(customer.getIdNumber());
                setName(customer.getName());
            }};
            var signature = Security.getSignature(Const.Appid, Const.secKey, date, payInfo);
            var response = payClient.pay(Const.Appid, date, signature, payInfo);
            var payOrder = Security.VerifyMapMessage(response, MockOrder.class);
            if (payOrder != null && payOrder.getPaySuccess()) {
                var flag = orderService.update(orderId);
                var storageFlag = storageService.decrease(order.getSeckillId());
                if (flag && storageFlag) {
                    return R.ok().data(new PaymentStatus() {{
                        setOrderId(orderId);
                        setPaymentSuccess(true);
                    }});
                } else {
                    return R.ok().data(new PaymentStatus() {{
                        setOrderId(orderId);
                        setPaymentSuccess(false);
                    }});
                }
            }
        }
        return R.ok().msg(MsgMapping.ACCOUNT_NOT_EXISTED).build();
    }
}
