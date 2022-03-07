package com.lingyuango.seckill.mock.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

/**
 * @author ChaconneLuo
 */

@Data
@ToString
public class Order {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer orderId;

    private Boolean paySuccess;

    private Integer accountId;

    private Integer money;

}
