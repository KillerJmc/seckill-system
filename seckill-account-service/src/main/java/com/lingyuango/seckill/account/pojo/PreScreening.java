package com.lingyuango.seckill.account.pojo;

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 初筛
 * @author Lingyuango
 */
@Data
@TableName("sk_pre_screening")
public class PreScreening {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 秒杀活动id
     */
    private Integer seckillId;

    /**
     * 客户id
     */
    private Integer accountId;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 是否通过初筛
     */
    private Boolean pass;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
