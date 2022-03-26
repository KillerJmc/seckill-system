package com.lingyuango.seckill.controller;

import com.jmc.net.R;
import com.lingyuango.seckill.common.Const;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.service.PreliminaryScreeningService;
import com.lingyuango.seckill.service.SeckillActivityService;
import com.lingyuango.seckill.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(originPatterns = Const.CROSS_ORIGIN, allowCredentials = "true")
public class SeckillActivityController {
    private final SeckillActivityService seckillActivityService;
    private final PreliminaryScreeningService preliminaryScreeningService;
    private final TokenService tokenService;

    @PostMapping("/getCurrentSeckillActivity")
    public synchronized R getCurrentSeckillActivity(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if (token == null || (customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON);
        }

        var activity = seckillActivityService.getLatest();

        // 往初筛表插入记录
        preliminaryScreeningService.insert(customerId);

        log.info("用户id {} 获取最新的秒杀活动：{}", customerId, activity);

        return R.ok()
                .msg("获取成功")
                .data(Map.of("activity", activity));
    }

    /**
     * 获取当前秒杀活动倒计时
     * @return 倒计时（单位：秒）
     */
    @PostMapping("/getCurrentSeckillCountDown")
    public synchronized R getCurrentSeckillCountDown(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if (token == null || (customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON);
        }

        var activity = seckillActivityService.getLatest();
        long countDown = Duration.between(LocalDateTime.now(), activity.getStartTime()).toSeconds();
        countDown = countDown < 0 ? 0 : countDown;

        log.info("用户id {} 获取最新的秒杀活动倒计时：{} 秒", customerId, activity);

        return R.ok()
                .data(Map.of("countDown", countDown));
    }
}
