package com.lingyuango.seckill.payment.common;

public interface MsgMapping {
    String ORDER_SEND_SUCCESS = "ORDER_SEND_SUCCESS";
    String PAY_SEND_SUCCESS = "PAY_SEND_SUCCESS";
    String ACCOUNT_NOT_EXISTED = "ACCOUNT_NOT_EXISTED";
    String PAY_REPEATED = "PAY_REPEATED";
    String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    String INSUFFICIENT_BALANCE = "INSUFFICIENT_BALANCE";
    String ORDER_OVERTIME = "ORDER_OVERTIME";
}
