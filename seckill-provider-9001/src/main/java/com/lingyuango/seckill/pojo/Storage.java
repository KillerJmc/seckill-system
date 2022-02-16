package com.lingyuango.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 库存
 * @author Jmc
 */
@Data
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

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;
}
