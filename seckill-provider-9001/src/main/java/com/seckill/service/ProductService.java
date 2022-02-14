package com.seckill.service;

import com.seckill.pojo.Product;

/**
 * @author Jmc
 */
public interface ProductService {
    Product getByProductId(Integer productId);
}
