package com.lingyuango.seckill.payment.pojo;

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 库存
 * @author Lingyuango
 */
@Data
@TableName("sk_storage")
public class Storage {

    @TableId(type = IdType.AUTO)
    private Integer id;

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
