package com.seckill.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Data
public class Admin {
    private Integer id;
    private String name;
    private String password;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
