package com.lingyuango.seckill.mock.dao;

import com.lingyuango.seckill.mock.pojo.MockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author ChaconneLuo
 */

@Repository
public interface OrderDao extends JpaRepository<MockOrder, Long> {
    @Query(nativeQuery = true, value = "select max(id) from `order`")
    Integer getMaxId();
}
