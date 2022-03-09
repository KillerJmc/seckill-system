package com.lingyuango.seckill.mock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.mock.pojo.CheckAccount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ChaconneLuo
 */

@Mapper
public interface CheckDao extends BaseMapper<CheckAccount> {

}
