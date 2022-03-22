package com.lingyuango.seckill.payment.service;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.ReceivePayMessage;

import java.io.IOException;

public interface PayService {

    R pay(ReceivePayMessage receivePayMessage) throws IOException;

}