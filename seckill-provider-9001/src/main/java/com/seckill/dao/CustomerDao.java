package com.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.pojo.Customer;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jmc
 */
@Mapper
public interface CustomerDao extends BaseMapper<Customer> {

}
