package com.lingyuango.seckill.mock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.mock.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountDao extends BaseMapper<Account> {

    @Select("select account_id from account where id_number = #{idNumber} and name = #{name}")
    Integer getAccountId(CheckAccount inf);

    @Select("select max(id) from account")
    Integer getMaxId();

}
