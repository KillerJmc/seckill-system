package com.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.pojo.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jmc
 */
@Mapper
public interface CustomerInfoDao extends BaseMapper<CustomerInfo> {
    @Select("select max(id) from customer_info")
    Integer getMaxId();
}
