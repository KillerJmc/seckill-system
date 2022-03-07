package com.lingyuango.seckill.mock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.mock.pojo.CheckAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ChaconneLuo
 */
@Mapper
public interface CheckDao extends BaseMapper<CheckAccount> {

    @Select("select id from account where number_id = #{idNumber} and name = #{name}")
    Integer userCount(CheckAccount Inf);

}
