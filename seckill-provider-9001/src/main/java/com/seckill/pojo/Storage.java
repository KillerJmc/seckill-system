package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 库存
 * @author Jmc
 */
@Data
public class Storage {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 秒杀活动id
     */
    private Integer seckillId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 库存数量
     */
    private Integer amount;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
