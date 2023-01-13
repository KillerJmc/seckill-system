package com.lingyuango.seckill.mock.pojo;

import com.lingyuango.seckill.mock.config.TypedReflective;
import lombok.Data;
import lombok.ToString;

/**
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
