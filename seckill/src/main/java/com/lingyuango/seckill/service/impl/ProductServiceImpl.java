package com.lingyuango.seckill.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lingyuango.seckill.dao.ProductDao;
import com.lingyuango.seckill.pojo.Product;
import com.lingyuango.seckill.service.ProductService;
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
