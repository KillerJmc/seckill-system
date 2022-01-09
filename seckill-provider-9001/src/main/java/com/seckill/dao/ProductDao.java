package com.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Jmc
 */
@Mapper
public interface ProductDao extends BaseMapper<Product> {

}
