package com.lingyuango.seckill.pojo;

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 秒杀活动规则
 * @author Lingyuango
 */
@Data
@TableName("sk_seckill_activity_rule")
public class SeckillActivityRule {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 规则id
     */
    private Integer ruleId;

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

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
