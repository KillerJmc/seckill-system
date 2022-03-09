package com.lingyuango.seckill.mock.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * @author ChaconneLuo
 */
@Data
@ToString
@TableName("account")
public class CheckAccount {

    private String idNumber;

    private String name;

}
