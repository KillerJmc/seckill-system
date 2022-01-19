package com.seckill.pojo;

import lombok.Data;

@Data
public class ActivityRule {
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
    private Integer maxOverdueMoney;
}
