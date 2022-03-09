package com.lingyuango.seckill.mock.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ChaconneLuo
 */
@Data
@TableName("account")
@JsonIgnoreProperties({"id","gmtCreate","gmtModified"})
public class Account {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String idNumber;

    private String name;

    private Integer accountId;

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;

}
