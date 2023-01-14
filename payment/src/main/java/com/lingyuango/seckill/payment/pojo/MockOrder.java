package com.lingyuango.seckill.payment.pojo;

import com.lingyuango.seckill.payment.config.TypedReflective;
import lombok.Data;
import lombok.ToString;


/**
 * 模拟模块订单类
 * @author ChaconneLuo
 */
@Data
@ToString
@TypedReflective
public class MockOrder {

    private Integer orderId;

    private Boolean paySuccess;

    private Integer accountId;

    private Double money;

}
