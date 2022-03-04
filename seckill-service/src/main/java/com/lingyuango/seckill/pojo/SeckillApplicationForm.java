package com.lingyuango.seckill.pojo;

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 秒杀活动客户申请表
 * @author Lingyuango
 */
@Data
@TableName("sk_seckill_application_form")
public class SeckillApplicationForm {

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

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
