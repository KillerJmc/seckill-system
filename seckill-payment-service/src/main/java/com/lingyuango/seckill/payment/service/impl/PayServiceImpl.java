package com.lingyuango.seckill.payment.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

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
    private final SeckillActivityClient seckillActivityClient;
    private final PayClient payClient;

    @Override
    @Transactional
    public synchronized R pay(ReceivePayMessage receivePayMessage) throws IOException {
        var orderId = receivePayMessage.getOrderId();
        var order = orderDao.selectOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderId, orderId));
        var customer = customerClient.get(order.getAccountId());
        var product = seckillActivityClient.getProduct(order.getSeckillId());

        var date = LocalDateTime.now();
        var checkAccount = new CheckAccountInfo() {{
            setName(customer.getName());
            setIdNumber(customer.getIdNumber());
        }};
        System.out.println(checkAccount);
        var checkSignature = Security.getSignature(Const.Appid, Const.secKey, date, checkAccount);
        var checkResponse = payClient.checkInformation(Const.Appid, date, checkSignature, checkAccount);

        var headers = checkResponse.headers();
        var checkInformation = JSON.parseObject(Arrays.toString(checkResponse.body().asInputStream().readAllBytes()), CheckAccountReturn.class);
        Boolean isSafe = Security.verify(Const.Appid,
                Const.secKey,
                CheckDateStamp.convert(String.valueOf(headers.get("Date-Stamp"))),
                String.valueOf(headers.get("Signature")),
                checkInformation);

        if (isSafe && checkInformation.getAccountExist()) {
            var payInformation = new MockPayInfo() {{
                setMoney(product.getPrice());
                setIdNumber(customer.getIdNumber());
                setName(customer.getName());
            }};
            var signature = Security.getSignature(Const.Appid, Const.secKey, date, payInformation);
            var response = payClient.pay(Const.Appid, date, signature, payInformation);
            var payOrder = JSON.parseObject(Arrays.toString(response.body().asInputStream().readAllBytes()), MockOrder.class);
            if (payOrder.getPaySuccess()) {
                var flag = orderService.update(orderId);
                var storageFlag = storageService.decrease(order.getSeckillId());
                if (flag && storageFlag) {
                    return R.ok().data(new MessageReturn() {{
                        setOrderId(orderId);
                        setBuildSuccess(true);
                    }});
                } else {
                    return R.ok().data(new MessageReturn() {{
                        setOrderId(orderId);
                        setBuildSuccess(false);
                    }});
                }

            }
        }
        return R.ok().msg(MsgMapping.ACCOUNT_NOT_EXISTED);
    }
}
