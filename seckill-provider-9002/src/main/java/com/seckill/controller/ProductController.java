package com.seckill.controller;

import com.seckill.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
}
