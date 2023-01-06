package com.lingyuango.seckill.payment.dao;

import com.lingyuango.seckill.payment.pojo.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Lingyuango
 */
@Repository
public interface StorageDao extends JpaRepository<Storage, Long> {
    Storage getOneBySeckillId(Integer seckillId);
}
