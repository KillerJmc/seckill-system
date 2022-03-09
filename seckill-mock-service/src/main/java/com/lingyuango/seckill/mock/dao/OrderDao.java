package com.lingyuango.seckill.mock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.mock.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ChaconneLuo
 */
@Mapper
public interface OrderDao extends BaseMapper<Order> {
    @Select("select max(id) from `order`")
    Integer getMaxId();
}
