package com.lingyuango.seckill.account.pojo;

import java.time.*;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

/**
 * 客户
 * @author Lingyuango
 */
@Data
@TableName("sk_customer")
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

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;
}
