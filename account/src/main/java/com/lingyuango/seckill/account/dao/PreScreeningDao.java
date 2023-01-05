package com.lingyuango.seckill.account.dao;

import com.lingyuango.seckill.account.pojo.PreScreening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jmc
 */
@Repository
public interface PreScreeningDao extends JpaRepository<PreScreening, Long> {

}
