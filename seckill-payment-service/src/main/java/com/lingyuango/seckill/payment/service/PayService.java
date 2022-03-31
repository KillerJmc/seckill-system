package com.lingyuango.seckill.payment.service;

import com.jmc.net.R;
import com.lingyuango.seckill.payment.pojo.PaymentStatus;

import java.io.IOException;

public interface PayService {

    R<PaymentStatus> pay(String orderId) throws IOException;

}
