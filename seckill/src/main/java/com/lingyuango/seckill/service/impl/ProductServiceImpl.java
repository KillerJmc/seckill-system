package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.dao.ProductDao;
import com.lingyuango.seckill.pojo.Product;
import com.lingyuango.seckill.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Override
    public Product getByProductId(Integer productId) {
        return productDao.getOneByProductId(productId);
    }
}
