package com.lingyuango.seckill.dao;

import com.lingyuango.seckill.pojo.SeckillActivity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author Jmc
 */
@Mapper
public interface SeckillActivityDao extends BaseMapper<SeckillActivity> {
    @Select("select product_id, amount, start_time, activity_info, activity_rule_id from sk_seckill_activity where id = (select max(id) from sk_seckill_activity)")
    SeckillActivity getLatest();

    @Select("select seckill_id from sk_seckill_activity where id = (select max(id) from sk_seckill_activity)")
    Integer getLatestSeckillId();
}
