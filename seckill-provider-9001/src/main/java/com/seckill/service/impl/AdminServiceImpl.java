package com.seckill.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.seckill.dao.AdminDao;
import com.seckill.pojo.Admin;
import com.seckill.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminDao adminDao;

    @Override
    public boolean login(Admin admin) {
        var queryAdmin = adminDao.selectOne(
            Wrappers.<Admin>lambdaQuery()
                    .eq(Admin::getName, admin.getName())
                    .eq(Admin::getPassword, admin.getPassword())
        );

        return queryAdmin != null;
    }
}
