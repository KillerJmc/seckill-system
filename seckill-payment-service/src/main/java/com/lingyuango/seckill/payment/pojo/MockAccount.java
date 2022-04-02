package com.lingyuango.seckill.payment.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * From com.lingyuango.seckill.mock.pojo.Account
 * @author ChaconneLuo
 */
@Data
public class MockAccount {

    private String idNumber;

    private String name;

    private Integer accountId;

}
