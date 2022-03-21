package com.lingyuango.seckill.payment.service;

import com.lingyuango.seckill.payment.pojo.OrderMessage;
import com.lingyuango.seckill.payment.pojo.ReceivePayMessage;

import java.io.IOException;

/**
 * @author Lingyuango
 */
public interface MessageService {

    void OrderHandle(OrderMessage orderMessage);

    void PayHandle(ReceivePayMessage receivePayMessage) throws IOException;
}
