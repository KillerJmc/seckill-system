package com.lingyuango.seckill.payment.service.impl;

import com.lingyuango.seckill.payment.service.StorageService;
import com.lingyuango.seckill.payment.dao.StorageDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final StorageDao storageDao;
}
