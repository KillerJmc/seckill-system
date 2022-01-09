package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Data
@TableName("seckill_success")
public class SeckillSuccess {
    private Integer id;
    private Integer seckillId;
    private Integer accountId;
    private String orderId;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
}
