package com.lingyuango.seckill.mock.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.mock.pojo.CheckAccount;
import com.lingyuango.seckill.mock.pojo.Money;
import com.lingyuango.seckill.mock.pojo.PayInformation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author ChaconneLuo
 */
@Mapper
public interface MoneyDao extends BaseMapper<Money> {

    @Insert("insert into money (account_id,money) value (#{accountId},#{money})")
    void insertMoney(Money money);

    @Select("select money where account_id = #{accountId}")
    Integer getMoney(Integer accountId);

    @Update("update money set money = #{money} where account_id = #{accountId}")
    void updateMoney(Integer accountId,Integer money);

    @Select("select account_id from account where number_id = #{idNumber} and name = #{name}")
    Integer getAccountId(PayInformation pay);

}
