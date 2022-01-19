package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
     * 活动中商品总量
     */
    private Integer amount;

    /**
     * 秒杀活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 秒杀活动信息
     */
    private String activityInfo;

    /**
     * 秒杀活动信息，用json储存，比如{age_max: 30, has_work: false} <br>
     * 结果转化为{@link ActivityRule}
     */
    private String activityRule;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
