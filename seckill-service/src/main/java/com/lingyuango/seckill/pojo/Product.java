package com.lingyuango.seckill.pojo;

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 商品
 * @author Lingyuango
 */
@Data
@TableName("sk_product")
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
