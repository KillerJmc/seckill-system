package com.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.seckill.dao.ProductDao;
import com.seckill.pojo.Product;
import com.seckill.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Override
    public Product getByProductId(Integer productId) {
        return productDao.selectOne(Wrappers.<Product>lambdaQuery().eq(Product::getProductId, productId));
    }
}
