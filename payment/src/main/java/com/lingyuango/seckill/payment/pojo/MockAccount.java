package com.lingyuango.seckill.payment.pojo;

import com.lingyuango.seckill.payment.config.TypedReflective;
import lombok.Data;

/**
 * From com.lingyuango.seckill.mock.pojo.Account
 * @author ChaconneLuo
 */
@Data
@TypedReflective
public class MockAccount {

    private String idNumber;

    private String name;

    private Integer accountId;

}
