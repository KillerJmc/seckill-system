package com.lingyuango.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀成功客户表
 * @author Jmc
 */
@Data
@TableName("seckill_success")
public class SeckillSuccess {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 秒杀活动id
     */
    private Integer seckillId;

    /**
     * 客户id
     */
    private Integer accountId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 订单价格
     */
    @TableField(select = false)
    private Double orderPrice;

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;
}
