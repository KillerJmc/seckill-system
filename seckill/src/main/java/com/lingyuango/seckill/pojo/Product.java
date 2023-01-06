package com.lingyuango.seckill.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品
 * @author Jmc
 */
@Data
@Entity(name = "sk_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Transient
    private LocalDateTime gmtCreate;

    @Transient
    private LocalDateTime gmtModified;
}
