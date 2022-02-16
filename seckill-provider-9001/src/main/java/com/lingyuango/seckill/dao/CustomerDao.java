package com.lingyuango.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jmc
 */
@Mapper
public interface CustomerDao extends BaseMapper<Customer> {
    @Select("select max(id) from customer")
    Integer getMaxId();
}
