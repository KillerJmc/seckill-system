package com.lingyuango.seckill.pojo;

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 秒杀活动
 * @author Lingyuango
 */
@Data
@TableName("sk_seckill_activity")
public class SeckillActivity {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 秒杀活动id
     */
    private Integer seckillId;

    /**
     * 商品id
     */
    private Integer productId;

    /**
     * 活动中商品总量
     */
    private Integer amount;

    /**
     * 秒杀活动开始时间
     */
    private LocalDateTime startTime;

    /**
     * 秒杀活动信息
     */
    private String activityInfo;

    /**
     * 秒杀规则id
     */
    private Integer activityRuleId;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
