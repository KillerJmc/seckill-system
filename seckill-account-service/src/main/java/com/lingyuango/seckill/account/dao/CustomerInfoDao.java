package com.lingyuango.seckill.account.dao;

import com.lingyuango.seckill.account.pojo.CustomerInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
