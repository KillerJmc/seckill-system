package com.seckill.service.impl;

import com.seckill.dao.ProductDao;
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
}
