package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 初筛
 * @author Jmc
 */
@Data
@TableName("preliminary_screening")
public class PreliminaryScreening {

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
     * 客户姓名
     */
    private String name;

    /**
     * 是否通过初筛
     */
    private Boolean pass;

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;
}
