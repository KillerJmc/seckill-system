package com.lingyuango.seckill.mock.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ChaconneLuo
 */
@Data
@TableName("money")
public class Money {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer accountId;

    private Integer money;

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;

}
