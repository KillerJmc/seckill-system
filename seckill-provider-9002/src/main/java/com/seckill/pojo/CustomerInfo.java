package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Data
@TableName("customer_info")
public class CustomerInfo {
    private Integer id;
    private Integer accountId;
    private Boolean workStatus;
    private Integer overdueTimes;
    private Double overdueMoney;
    private Integer overdueDays;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
