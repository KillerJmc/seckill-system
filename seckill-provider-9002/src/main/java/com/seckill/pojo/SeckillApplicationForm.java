package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Data
@TableName("seckill_application_form")
public class SeckillApplicationForm {
    private Integer id;
    private Integer seckillId;
    private Integer accountId;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
