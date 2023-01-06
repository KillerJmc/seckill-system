package com.lingyuango.seckill.account.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户
 * @author Jmc
 */
@Data
@Entity(name = "sk_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Transient
    private LocalDateTime gmtCreate;

    @Transient
    private LocalDateTime gmtModified;
}
