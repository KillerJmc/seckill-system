package com.lingyuango.seckill.mock.dao;

import com.lingyuango.seckill.mock.pojo.MockPayInfo;
import com.lingyuango.seckill.mock.pojo.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author ChaconneLuo
 */
@Repository
public interface MoneyDao extends JpaRepository<Money, Long> {
    @Query(nativeQuery = true, value = "select account_id from account where id_number = ?#{#pay.idNumber} and name = ?#{#pay.name}")
    Integer getAccountId(MockPayInfo pay);

    Money getOneByAccountIdIs(Integer accountId);
}
