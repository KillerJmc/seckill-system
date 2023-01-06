package com.lingyuango.seckill.account.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 初筛
 * @author Jmc
 */
@Data
@Entity(name = "sk_pre_screening")
public class PreScreening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 秒杀活动id
     */
    private Integer seckillId;

    /**
     * 客户id
     */
    private Integer account;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 是否通过初筛
     */
    private Boolean pass;

    @Transient
    private LocalDateTime gmtCreate;

    @Transient
    private LocalDateTime gmtModified;
}
