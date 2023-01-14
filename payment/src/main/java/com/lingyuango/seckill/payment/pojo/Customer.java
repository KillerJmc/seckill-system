package com.lingyuango.seckill.payment.pojo;

import com.lingyuango.seckill.payment.config.TypedReflective;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户
 * @author Jmc
 */
@Data
@TypedReflective
public class Customer {

    private Long id;

    /**
     * 账号，等于自然主键 + 10000
     */
    private Integer account;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 账号密码
     */
    private String password;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
