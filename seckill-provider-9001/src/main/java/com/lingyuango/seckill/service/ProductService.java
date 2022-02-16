package com.lingyuango.seckill.service;

import com.lingyuango.seckill.pojo.Product;

/**
 * @author Jmc
 */
public interface ProductService {
    Product getByProductId(Integer productId);
}
