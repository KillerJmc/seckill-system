package com.lingyuango.seckill.dao;

import com.lingyuango.seckill.pojo.SeckillApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jmc
 */
@Repository
public interface SeckillApplicationFormDao extends JpaRepository<SeckillApplicationForm, Long> {
    SeckillApplicationForm getOneByAccountId(Integer accountId);
}
