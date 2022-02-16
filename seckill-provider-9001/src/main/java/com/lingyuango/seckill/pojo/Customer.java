package com.lingyuango.seckill.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 客户
 * @author Jmc
 */
@Data
public class Customer {

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 账号id，等于自然主键 + 10000
     */
    private Integer accountId;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 客户姓名
     */
    private String name;

    /**
     * 账号密码
     */
    private String password;

    @TableField(select = false)
    private LocalDateTime gmtCreate;

    @TableField(select = false)
    private LocalDateTime gmtModified;
}
