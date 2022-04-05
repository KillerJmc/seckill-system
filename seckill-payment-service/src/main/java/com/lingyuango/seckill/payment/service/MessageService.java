package com.lingyuango.seckill.payment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lingyuango.seckill.payment.pojo.BasicOrder;

import java.io.IOException;

/**
 * @author Lingyuango
 */
public interface MessageService {

    void orderHandle(BasicOrder basicOrder) throws JsonProcessingException;

    void payHandle(String orderId) throws IOException;
}
