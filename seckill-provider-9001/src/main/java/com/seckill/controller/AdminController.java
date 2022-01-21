package com.seckill.controller;

import com.jmc.net.R;
import com.seckill.common.Const;
import com.seckill.common.MsgMapping;
import com.seckill.pojo.Admin;
import com.seckill.pojo.SeckillActivity;
import com.seckill.service.AdminService;
import com.seckill.service.TokenService;
import com.seckill.util.Verify;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final TokenService tokenService;

    /**
     * 登录（传入账号，密码）<br>
     * 要对参数进行校验，把token放好，小心多服务间token共享问题 <br>
     * 返回tokeGet户端（token用{uuid} -> adminName存进redis）
     */
    @PostMapping("/admin/login")
    public synchronized R login(Admin admin) {
        if (Verify.nullOrEmpty(admin.getName(), admin.getPassword())) {
            return R.error()
                    .msg(MsgMapping.ACCOUNT_PWD_NULL_OR_EMPTY);
        }

        if (!adminService.login(admin)) {
            return R.error()
                    .msg(MsgMapping.ACCOUNT_OR_PWD_ERROR);
        }

        // 定义token
        var token = UUID.randomUUID().toString();

        // 存入redis
        tokenService.putAccountName(token, admin.getName());

        // 返回token给客户端
        return R.ok()
                .msg(MsgMapping.LOGIN_SUCCESS)
                .data(Map.of("token", token));
    }

    /**
     * 获取秒杀事件 <br>
     */
    @PostMapping("/admin/getSeckillActivity")
    public R getSeckillActivity(String token) {
        return null;
    }

    /**
     * 添加秒杀事件 <br>
     * 利用redis expire来做倒计时，开一个线程检测redis数据是否过期<br>
     * 如果过期就生成秒杀链接（seckillId-{seckillId}-url->{url}）
     */
    @PostMapping("/admin/addSeckillActivity")
    public R addSeckillActivity(String token, SeckillActivity seckillActivity) {
        return null;
    }

    /**
     * 获取秒杀成功的客户列表
     *
     * @param seckillId 秒杀id
     */
    @PostMapping("/admin/getSeckillSuccessList")
    public R getSeckillSuccessList(String token, Integer seckillId) {
        return null;
    }

    /**
     * 获取秒杀链接 <br>
     * 注意，从redis中获取（秒杀链接在活动开始时会放进redis） <br>
     */
    @PostMapping("/admin/getSeckillUrl")
    public R getSeckillUrl(String token, Integer seckillId) {
        return null;
    }
}
