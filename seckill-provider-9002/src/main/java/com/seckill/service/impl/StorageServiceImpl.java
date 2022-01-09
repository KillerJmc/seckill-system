package com.seckill.service.impl;

import com.seckill.dao.StorageDao;
import com.seckill.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageDao storageDao;
}
