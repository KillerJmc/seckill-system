package com.lingyuango.seckill.mock.pojo;

import com.lingyuango.seckill.mock.config.TypedReflective;
import lombok.Data;

@Data
@TypedReflective
public class MockCreditInfo {
    /**
     * 工作状态
     */
    private Boolean workStatus;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 是否在失信人名单中
     */
    private Boolean inCreditBlacklist;

    /**
     * 三年内逾期次数
     */
    private Integer overdueTimes;

    /**
     * 三年内逾期天数
     */
    private Integer overdueDays;

    /**
     * 三年内逾期金额
     */
    private Double overdueMoney;
}
