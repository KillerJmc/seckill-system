package com.lingyuango.seckill.mock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.mock.pojo.Money;
import com.lingyuango.seckill.mock.pojo.PayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author ChaconneLuo
 */
@Mapper
public interface MoneyDao extends BaseMapper<Money> {

    @Select("select account_id from account where id_number = #{idNumber} and name = #{name}")
    Integer getAccountId(PayInfo pay);

}
