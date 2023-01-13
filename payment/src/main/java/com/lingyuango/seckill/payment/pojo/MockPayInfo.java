package com.lingyuango.seckill.payment.pojo;

import com.lingyuango.seckill.payment.config.TypedReflective;
import lombok.Data;
import lombok.ToString;

/**
 * 发给模拟模块的支付信息
 * @author ChaconneLuo
 */
@Data
@ToString
@TypedReflective
public class MockPayInfo {

    private String idNumber;

    private String name;

    private Double money;

}
