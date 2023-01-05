package com.lingyuango.seckill.dao;

import com.lingyuango.seckill.pojo.SeckillActivityRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jmc
 */
@Repository
public interface SeckillActivityRuleDao extends JpaRepository<SeckillActivityRule, Long> {

}
