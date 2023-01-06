package com.lingyuango.seckill.mock.dao;

import com.lingyuango.seckill.mock.pojo.MockAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<MockAccount, Long> {

    @Query(nativeQuery = true, value = "select account_id from account where id_number = ?#{#inf.idNumber} and name = ?#{#inf.name}")
    Integer getAccountId(MockAccount inf);

    @Query(nativeQuery = true, value = "select max(id) from account")
    Integer getMaxId();

}
