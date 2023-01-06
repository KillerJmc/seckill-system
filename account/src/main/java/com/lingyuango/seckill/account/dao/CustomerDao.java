package com.lingyuango.seckill.account.dao;

import com.lingyuango.seckill.account.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Jmc
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {
    @Query(nativeQuery = true, value = "select max(id) from sk_customer")
    Integer getMaxId();

    boolean existsByIdNumber(String idNumber);

    Customer getOneByAccount(Integer account);
}
