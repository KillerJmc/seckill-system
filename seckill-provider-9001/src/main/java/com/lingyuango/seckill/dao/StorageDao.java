package com.lingyuango.seckill.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lingyuango.seckill.pojo.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * @author Jmc
 */
@Mapper
public interface StorageDao extends BaseMapper<Storage> {
    @Update("update storage set amount = amount - 1 where seckill_id = #{seckillId}")
    int decrease(Integer seckillId);
}
