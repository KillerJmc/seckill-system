package com.lingyuango.seckill.account.pojo;

import com.lingyuango.seckill.account.config.TypedReflective;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀活动规则
 * @author Jmc
 */
@Data
@TypedReflective
public class SeckillActivityRule {
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

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
