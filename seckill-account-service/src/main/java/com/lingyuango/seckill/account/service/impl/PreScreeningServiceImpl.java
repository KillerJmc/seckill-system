package com.lingyuango.seckill.account.service.impl;

import com.lingyuango.seckill.account.service.PreScreeningService;
import com.lingyuango.seckill.account.dao.PreScreeningDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Lingyuango
 */
@Service
@RequiredArgsConstructor
public class PreScreeningServiceImpl implements PreScreeningService {
    private final PreScreeningDao preScreeningDao;
}
