package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 商品
 * @author Jmc
 */
@Data
public class Product {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品介绍
     */
    private String desc;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
