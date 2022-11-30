package com.lingyuango.seckill.account.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.account.pojo.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jmc
 */
@Mapper
public interface CustomerInfoDao extends BaseMapper<CustomerInfo> {
    @Select("select max(id) from sk_customer_info")
    Integer getMaxId();
}
