package com.lingyuango.seckill.mock.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author ChaconneLuo
 */

@Data
@ToString
@TableName("`order`")
@JsonIgnoreProperties({"id","gmtCreate","gmtModified"})
public class MockOrder {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer orderId;

    private Boolean paySuccess;

    private Integer accountId;

    private double money;

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;
}
