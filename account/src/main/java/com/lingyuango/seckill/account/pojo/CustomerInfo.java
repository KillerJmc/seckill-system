package com.lingyuango.seckill.account.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户信息
 * @author Jmc
 */
@Data
@Entity(name = "sk_customer_info")
public class CustomerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 账号
     */
    private Integer account;

    /**
     * 是否有工作
     */
    private Boolean workStatus;

    /**
     * 是否被列入失信人名单
     */
    private Boolean inCreditBlacklist;

    /**
     * 三年内逾期次数
     */
    private Integer overdueTimes;

    /**
     * 三年内逾期金额
     */
    private Double overdueMoney;

    /**
     * 三年内逾期天数
     */
    private Integer overdueDays;

    @Transient
    private LocalDateTime gmtCreate;

    @Transient
    private LocalDateTime gmtModified;
}
