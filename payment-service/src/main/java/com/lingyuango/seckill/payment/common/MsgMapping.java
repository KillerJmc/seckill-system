package com.lingyuango.seckill.payment.common;

public interface MsgMapping {
    String ORDER_SEND_SUCCESS = "ORDER_SEND_SUCCESS";
    String PAY_SEND_SUCCESS = "PAY_SEND_SUCCESS";
    String ACCOUNT_NOT_EXISTED = "ACCOUNT_NOT_EXISTED";
    String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    String INSUFFICIENT_BALANCE = "余额不足";
    String ORDER_OVERTIME = "订单超时";
    String ORDER_NOT_READY = "订单未准备好";
    String PAYMENT_STATUS_NOT_READY = "支付状态信息未准备好";
}
