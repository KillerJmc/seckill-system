package com.lingyuango.seckill.controller;

import com.jmc.lang.Strs;
import com.jmc.net.R;
import com.lingyuango.seckill.client.PreScreeningClient;
import com.lingyuango.seckill.client.SeckillSuccessClient;
import com.lingyuango.seckill.client.StorageClient;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.pojo.*;
import com.lingyuango.seckill.service.*;
import com.lingyuango.seckill.service.impl.ProductServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/seckillActivity")
@Slf4j
public class SeckillActivityController {
    private final TokenService tokenService;
    private final SeckillActivityService seckillActivityService;
    private final SeckillApplicationFormService seckillApplicationFormService;
    private final StorageClient storageClient;
    private final SeckillSuccessClient seckillSuccessClient;
    private final PreScreeningClient preScreeningClient;

    /**
     * 获取最新规则
     */
    @PostMapping("/getRule")
    public R<SeckillActivityRule> getRule() {
        SeckillActivityRule res;
        if ((res = seckillActivityService.getRule()) == null) {
            return R.error()
                    .build();
        } else {
            return R.ok()
                    .data(res);
        }
    }

    /**
     * 获取商品信息
     * @return
     */
    @PostMapping("/getProduct")
    public R<Product> getProduct() {
        var activity = seckillActivityService.getLatest();
        return R.ok()
                .data(activity.getProduct());
    }

    /**
     * 获取最新的秒杀id
     */
    @PostMapping("/getSeckillId")
    public R<Integer> getSeckillId() {
        var seckillId = seckillActivityService.getLatestSeckillId();
        return seckillId == null ? R.error().msg("Latest seckillId is null").build() : R.ok().data(seckillId);
    }

    @PostMapping("/getCurrent")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public synchronized R<SeckillActivity> getCurrent(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if (token == null || (customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var activity = seckillActivityService.getLatest();

        // 往初筛表插入记录
        preScreeningClient.insert(customerId).get();

        log.info("用户id {} 获取最新的秒杀活动：{}", customerId, activity);

        return R.ok()
                .data(activity);
    }

    /**
     * 获取当前秒杀活动倒计时
     * @return 倒计时（单位：秒）
     */
    @PostMapping("/getCountDown")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public synchronized R<Long> getCountDown(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if (token == null || (customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var activity = seckillActivityService.getLatest();
        long countDown = Duration.between(LocalDateTime.now(), activity.getStartTime()).toSeconds();
        countDown = countDown < 0 ? 0 : countDown;

        log.info("用户id {} 获取最新的秒杀活动倒计时：{} 秒", customerId, activity);

        return R.ok()
                .data(countDown);
    }

    /**
     * 申请秒杀
     */
    @PostMapping("/apply")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public synchronized R<Void> apply(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        if (seckillApplicationFormService.contains(customerId)) {
            return R.error()
                    .msg(MsgMapping.APPLY_REPEAT)
                    .build();
        }

        var insertSuccess = seckillApplicationFormService.insert(customerId);
        return insertSuccess ? R.ok().msg(MsgMapping.APPLY_SUCCESS).build() :
                R.error().msg(MsgMapping.APPLY_FAILED).build();
    }

    /**
     * 获取暴露的秒杀地址
     */
    @PostMapping("/getSeckillUrl")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public R<String> getSeckillUrl(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var applied = seckillApplicationFormService.contains(customerId);
        // 检查是否申请
        if (!applied) {
            return R.error()
                    .msg(MsgMapping.DOES_NOT_APPLY)
                    .build();
        }

        var between = Duration.between(LocalDateTime.now(), seckillActivityService.getLatest().getStartTime());

        // 检查秒杀是否开始
        if (between.toMillis() > 0) {
            return R.error()
                    .msg(MsgMapping.SECKILL_NOT_STARTED)
                    .build();
        }

        var seckillId = seckillActivityService.getLatestSeckillId();
        log.info("用户id {} 获取秒杀链接 {}", customerId, seckillId);

        return R.ok()
                .data(seckillId.toString());
    }

    /**
     * 秒杀接口
     * @return 成功的话返回订单
     */
    @PostMapping("/seckill/{seckillUrl}")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public R<Map<String, SeckillSuccess>> seckill(@CookieValue(value = "token", required = false) String token, @PathVariable String seckillUrl) {
        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var applied = seckillApplicationFormService.contains(customerId);
        // 检查是否申请
        if (!applied) {
            return R.error()
                    .msg(MsgMapping.DOES_NOT_APPLY)
                    .build();
        }

        // 检查秒杀链接格式
        if (!Strs.isNum(seckillUrl)) {
            return R.error()
                    .msg(MsgMapping.INVALID_SECKILL_URL)
                    .build();
        }

        var seckillUrlInt = Integer.valueOf(seckillUrl);
        var seckillId = seckillActivityService.getLatestSeckillId();
        // 检查秒杀链接
        if (!seckillId.equals(seckillUrlInt)) {
            return R.error()
                    .msg(MsgMapping.INVALID_SECKILL_URL)
                    .build();
        }

        var storage = storageClient.getStorage(seckillId).get();

        log.info("用户id：{}，当前活动库存为：{}", customerId, storage);

        // 检查重复购买
        if (seckillSuccessClient.contains(seckillId, customerId).get()) {
            return R.error()
                    .msg(MsgMapping.PURCHASE_REPEAT)
                    .build();
        }

        // 检查库存
        if (storage <= 0) {
            return R.error()
                    .msg(MsgMapping.PRODUCT_SOLD_OUT)
                    .build();
        }

        // 减少库存
        storageClient.decrease(seckillId);

        log.info("用户id：{}，秒杀成功", customerId);

        var order = seckillSuccessClient.insert(seckillId, customerId).get();

        return R.ok()
                .msg(MsgMapping.SECKILL_SUCCESS)
                .data(Map.of("order", order));
    }
}
