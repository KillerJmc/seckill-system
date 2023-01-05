package com.lingyuango.seckill.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀活动
 * @author Jmc
 */
@Data
@Entity(name = "sk_seckill_activity")
public class SeckillActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:SS")
    private LocalDateTime startTime;

    /**
     * 秒杀活动信息
     */
    private String activityInfo;

    /**
     * 秒杀活动规则id
     */
    private Long activityRuleId;

    @Transient
    private LocalDateTime gmtCreate;

    @Transient
    private LocalDateTime gmtModified;

    /**
     * 秒杀活动商品（手动注入）
     */
    @Transient
    private Product product;

    /**
     * 秒杀活动规则（手动注入）
     */
    @Transient
    private SeckillActivityRule rule;
}
