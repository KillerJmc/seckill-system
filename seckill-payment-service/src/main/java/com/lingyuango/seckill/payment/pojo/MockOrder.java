package com.lingyuango.seckill.payment.pojo;

import lombok.Data;
import lombok.ToString;


/**
 * 模拟模块订单类
 * @author ChaconneLuo
 */
@Data
@ToString
public class MockOrder {

    private Integer orderId;

    private Boolean paySuccess;

    private Integer accountId;

    private Integer money;

}
