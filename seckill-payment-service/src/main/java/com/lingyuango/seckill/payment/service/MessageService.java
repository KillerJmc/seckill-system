package com.lingyuango.seckill.payment.service;

import com.lingyuango.seckill.payment.pojo.BasicOrder;

import java.io.IOException;

/**
 * @author Lingyuango
 */
public interface MessageService {

    void OrderHandle(BasicOrder basicOrder);

    void PayHandle(ReceivePayMessage receivePayMessage) throws IOException;
}
