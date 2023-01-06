package com.lingyuango.seckill.mock.dao;

import com.lingyuango.seckill.mock.pojo.MockAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ChaconneLuo
 */

@Repository
public interface CheckDao extends JpaRepository<MockAccount, Long> {
    Long countByIdNumber(String idNumber);
}
