package com.seckill.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Data
public class Product {
    private Integer id;
    private Integer productId;
    private String name;
    private String desc;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
