package com.lingyuango.seckill.account.dao;

import com.lingyuango.seckill.account.pojo.CustomerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jmc
 */
@Repository
public interface CustomerInfoDao extends JpaRepository<CustomerInfo, Long> {
    CustomerInfo getOneByAccount(Integer account);
}
