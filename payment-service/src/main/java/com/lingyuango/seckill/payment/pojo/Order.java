package com.lingyuango.seckill.payment.pojo;

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 秒杀成功客户表
 * @author Lingyuango
 */
@Data
@TableName("sk_seckill_success")
public class Order {

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

    private Boolean paid;

    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;
}
