package com.lingyuango.seckill.payment.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author ChaconneLuo
 */
@Data
@ToString
@TableName("account")
public class CheckAccountInf {

    private String idNumber;

    private String name;

}
