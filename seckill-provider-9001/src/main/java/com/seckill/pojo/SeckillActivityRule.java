package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀活动规则
 * @author Jmc
 */
@Data
@TableName("seckill_activity_rule")
public class SeckillActivityRule {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 规则名称
     */
    private String ruleName;

    /**
     * 工作状态（在岗或失业）
     */
    private Boolean workStatus;

    /**
     * 最小年龄
     */
    private Integer minAge;

    /**
     * 最大年龄
     */
    private Integer maxAge;

    /**
     * 是否在失信人名单中
     */
    private Boolean inCreditBlacklist;

    /**
     * 三年内最多逾期次数
     */
    private Integer maxOverdueTimes;

    /**
     * 三年内最多逾期天数
     */
    private Integer maxOverdueDays;

    /**
     * 三年内最多逾期金额
     */
    private Double maxOverdueMoney;

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;
}
