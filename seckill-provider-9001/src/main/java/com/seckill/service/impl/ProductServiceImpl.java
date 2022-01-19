package com.seckill.service.impl;

import com.seckill.dao.ProductDao;
import com.seckill.pojo.Product;
import com.seckill.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;

    @Override
    public List<Product> getList() {
        return null;
    }
}
