package com.lingyuango.seckill.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀活动规则
 * @author Jmc
 */
@Data
@Entity(name = "sk_seckill_activity_rule")
public class SeckillActivityRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 规则id
     */
    private String ruleId;

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

    @Transient
    private LocalDateTime gmtCreate;

    @Transient
    private LocalDateTime gmtModified;
}
