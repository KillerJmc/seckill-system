package com.seckill.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Data
public class Storage {
    private Integer id;
    private Integer seckillId;
    private Integer productId;
    private Integer amount;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
