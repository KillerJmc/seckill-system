package com.lingyuango.seckill.account.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户信息
 * @author Jmc
 */
@Data
@TableName("sk_customer_info")
public class CustomerInfo {

    @TableId(type = IdType.AUTO)
    private Integer id;

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

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;
}
