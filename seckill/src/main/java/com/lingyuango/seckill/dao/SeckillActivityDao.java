package com.lingyuango.seckill.dao;

import com.lingyuango.seckill.pojo.SeckillActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Jmc
 */
@Repository
public interface SeckillActivityDao extends JpaRepository<SeckillActivity, Long> {
    @Query(nativeQuery = true, value = "select * from sk_seckill_activity where id = (select max(id) from sk_seckill_activity)")
    SeckillActivity getLatest();

    @Query(nativeQuery = true, value = "select seckill_id from sk_seckill_activity where id = (select max(id) from sk_seckill_activity)")
    Integer getLatestSeckillId();
}
