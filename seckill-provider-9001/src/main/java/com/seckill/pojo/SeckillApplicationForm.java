package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 秒杀活动客户申请表
 * @author Jmc
 */
@Data
@TableName("seckill_application_form")
public class SeckillApplicationForm {

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
