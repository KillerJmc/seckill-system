package com.lingyuango.seckill.service.impl;

import com.lingyuango.seckill.service.ProductService;
import com.lingyuango.seckill.dao.ProductDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductDao productDao;
}
