package com.lingyuango.seckill.pojo;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀活动客户申请表
 * @author Jmc
 */
@Data
@Entity(name = "sk_seckill_application_form")
public class SeckillApplicationForm {

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
    private Integer accountId;

    @Transient
    private LocalDateTime gmtCreate;

    @Transient
    private LocalDateTime gmtModified;
}
