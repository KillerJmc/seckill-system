package com.lingyuango.seckill.payment.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品
 * @author Jmc
 */
@Data
public class Product {

    private Long id;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品价格
     */
    private Double price;

    /**
     * 商品信息
     */
    private String info;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
