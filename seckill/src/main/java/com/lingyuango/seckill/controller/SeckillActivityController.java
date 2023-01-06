package com.lingyuango.seckill.controller;

import com.jmc.net.R;
import com.jmc.util.Rand;
import com.lingyuango.seckill.client.PreScreeningClient;
import com.lingyuango.seckill.common.MsgMapping;
import com.lingyuango.seckill.pojo.BasicOrder;
import com.lingyuango.seckill.pojo.PaymentStatus;
import com.lingyuango.seckill.pojo.SeckillActivity;
import com.lingyuango.seckill.service.PaymentService;
import com.lingyuango.seckill.service.SeckillActivityService;
import com.lingyuango.seckill.service.SeckillApplicationFormService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/seckillActivity")
@Slf4j
public class SeckillActivityController {
    private final SeckillActivityService seckillActivityService;
    private final SeckillApplicationFormService seckillApplicationFormService;
    private final PreScreeningClient preScreeningClient;
    private final PaymentService paymentService;

    /**
     * 初始化在Redis中秒杀活动的基本信息
     */
    @PostConstruct
    public void initActivityInfoInRedis() {
        seckillActivityService.initRedis();
    }

    /**
     * 获取秒杀活动信息
     */
    @GetMapping("/getCurrent")
    public R<SeckillActivity> getCurrent(@CookieValue("account") Integer account) {
        return R.stream()
                // 往初筛表插入记录
                .exec(() -> preScreeningClient.insert(account))
                // 获取秒杀活动
                .build(seckillActivityService::getLatest);
    }

    /**
     * 获取秒杀活动倒计时
     * @return 倒计时（单位：秒）
     */
    @GetMapping("/getCountDown")
    public R<Long> getCountDown() {
        return R.ok(seckillActivityService.getCountDown());
    }

    /**
     * 申请秒杀
     */
    @PostMapping("/apply")
    public R<Void> apply(@CookieValue("account") Integer account) {
        return R.stream()
                // 检查是否重复申请
                .check(seckillApplicationFormService.contains(account), MsgMapping.APPLY_REPEAT)
                // 插入申请信息
                .exec(() -> seckillApplicationFormService.insert(account))
                .build();
    }

    /**
     * 获取暴露的秒杀地址
     */
    @GetMapping("/getSeckillUrl")
    public R<String> getSeckillUrl(@CookieValue("account") Integer account) {
        return R.stream()
                // 检查是否申请
                .check(!seckillApplicationFormService.contains(account), MsgMapping.DOES_NOT_APPLY)
                // 检查秒杀是否还没开始
                .check(seckillActivityService.isSeckillNotStarted(), MsgMapping.SECKILL_NOT_STARTED)
                .exec(() -> log.info("用户id {} 获取秒杀链接", account))
                // 获取秒杀链接
                .build(() -> seckillActivityService.getLatestSeckillId().toString());
    }

    /**
     * 秒杀接口
     */
    @PostMapping("/seckill/{seckillUrl}")
    public R<Void> seckill(@CookieValue("account") Integer account,
                           @PathVariable String seckillUrl) {
        return R.stream()
                // 检查是否申请
                .check(!seckillApplicationFormService.contains(account), MsgMapping.DOES_NOT_APPLY)
                // 秒杀
                .exec(() -> seckillActivityService.seckill(seckillUrl, account))
                .build();
    }

    /**
     * 测试秒杀接口
     */
    @PostMapping("/testSeckill")
    public R<Void> testSeckill(@CookieValue("account") Integer account, String seckillUrl) {
        return R.stream()
                // 检查是否申请
                .check(!seckillApplicationFormService.contains(account), MsgMapping.DOES_NOT_APPLY)
                // 用假帐户模拟秒杀
                .exec(() -> seckillActivityService.seckill(seckillUrl, Rand.nextInt()))
                .build();
    }


    /**
     * 获取订单
     */
    @GetMapping("/getOrder")
    public R<BasicOrder> getOrder(@CookieValue("account") Integer account) {
        return R.stream()
                .build(() -> paymentService.getOrder(account));
    }

    /**
     * 支付
     */
    @PostMapping("/pay")
    public R<PaymentStatus> pay(@CookieValue("account") Integer account, String orderId) {
        return R.stream()
                .build(() -> paymentService.pay(account, orderId));
    }
}
