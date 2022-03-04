package com.lingyuango.seckill.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.service.ProductService;

/**
 * @author Lingyuango
 */
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
}
