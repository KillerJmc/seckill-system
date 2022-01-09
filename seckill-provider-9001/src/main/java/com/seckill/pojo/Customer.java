package com.seckill.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Data
public class Customer {
    private Integer id;
    private Integer accountId;
    private String idNumber;
    private String name;
    private String password;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
