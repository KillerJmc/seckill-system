package com.lingyuango.seckill.dao;

import com.lingyuango.seckill.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Jmc
 */
@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
    Product getOneByProductId(Integer productId);
}
