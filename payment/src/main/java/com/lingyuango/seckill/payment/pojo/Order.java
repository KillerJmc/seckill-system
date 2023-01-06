package com.lingyuango.seckill.payment.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀成功客户表
 * @author Lingyuango
 */
@Data
@Entity(name = "sk_seckill_success")
public class Order {

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

    /**
     * 订单id
     */
    private String orderId;

    private Boolean paid;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
