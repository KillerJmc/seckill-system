package com.lingyuango.seckill.payment.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 库存
 * @author Lingyuango
 */
@Data
@Entity(name = "sk_storage")
public class Storage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 秒杀活动id
     */
    private Integer seckillId;

    /**
     * 库存数量
     */
    private Integer amount;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
