package com.lingyuango.seckill.account.pojo;

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 客户信息
 * @author Lingyuango
 */
@Data
@TableName("sk_customer_info")
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
