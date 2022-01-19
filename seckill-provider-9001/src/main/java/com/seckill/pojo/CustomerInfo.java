package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户信息
 * @author Jmc
 */
@Data
@TableName("customer_info")
public class CustomerInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 客户id
     */
    private Integer accountId;

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

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
