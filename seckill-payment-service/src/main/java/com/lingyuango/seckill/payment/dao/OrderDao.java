package com.lingyuango.seckill.payment.dao;

import com.lingyuango.seckill.payment.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lingyuango
 */
@Mapper
public interface OrderDao extends BaseMapper<Order> {

}
