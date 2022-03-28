package com.lingyuango.seckill.payment.service;

import com.jmc.net.R;

import java.io.IOException;

public interface PayService {

    R pay(ReceivePayMessage receivePayMessage) throws IOException;

}
