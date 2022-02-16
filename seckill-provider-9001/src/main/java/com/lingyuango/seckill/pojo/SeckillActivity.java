package com.lingyuango.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀活动
 * @author Jmc
 */
@Data
@TableName("seckill_activity")
public class SeckillActivity {

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
     * 秒杀活动商品（手动注入）
     */
    @TableField(select = false)
    private Product product;

    /**
     * 活动中商品总量
     */
    private Integer amount;

    /**
     * 秒杀活动开始时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime startTime;

    /**
     * 秒杀活动信息
     */
    private String activityInfo;

    /**
     * 秒杀活动规则id
     */
    private Integer activityRuleId;

    /**
     * 秒杀活动规则（手动注入）
     */
    @TableField(select = false)
    private SeckillActivityRule activityRule;

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;
}
