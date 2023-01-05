package com.lingyuango.seckill.mock.dao;

import com.lingyuango.seckill.mock.pojo.SecretKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ChaconneLuo
 */

@Repository
public interface SecretKeyDao extends JpaRepository<SecretKey, Long> {
    SecretKey getOneByAppIdIs(String appId);
}
