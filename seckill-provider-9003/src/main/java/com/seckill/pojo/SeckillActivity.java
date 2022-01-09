package com.seckill.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Jmc
 */
@Data
@TableName("seckill_activity")
public class SeckillActivity {
    private Integer id;
    private Integer seckillId;
    private Integer productId;
    private Integer amount;
    private LocalDateTime startTime;
    private String activityInfo;
    private String activityRule;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
}
