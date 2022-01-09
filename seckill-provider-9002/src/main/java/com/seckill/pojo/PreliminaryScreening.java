package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Data
@TableName("preliminary_screening")
public class PreliminaryScreening {
    private Integer id;
    private Integer accountId;
    private String name;
    private Boolean pass;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
