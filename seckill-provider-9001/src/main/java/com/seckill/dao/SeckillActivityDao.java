package com.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seckill.pojo.SeckillActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jmc
 */
@Mapper
public interface SeckillActivityDao extends BaseMapper<SeckillActivity> {
    @Select("select product_id, amount, start_time, activity_info, activity_rule_id from seckill_activity where id = (select max(id) from seckill_activity)")
    SeckillActivity getLatest();

    @Select("select seckill_id from seckill_activity where id = (select max(id) from seckill_activity)")
    int getLatestSeckillId();
}
