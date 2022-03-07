package com.lingyuango.seckill.mock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.mock.pojo.Account;
import com.lingyuango.seckill.mock.pojo.CheckAccount;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AccountDao extends BaseMapper<Account> {

    @Insert("insert into account (id_number,name,account_id) value (#{idNumber},#{name},max(id)+10001)")
    void insertAccount(CheckAccount inf);

    @Select("select account_id from account where number_id = #{idNumber} and name = #{name}")
    Integer getAccountId(CheckAccount inf);

    @Select("select max(id) from account")
    Integer getMaxId();

}
