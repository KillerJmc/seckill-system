package com.seckill.controller;

import com.jmc.net.R;
import com.seckill.common.Const;
import com.seckill.common.MsgMapping;
import com.seckill.pojo.Customer;
import com.seckill.pojo.PreliminaryScreening;
import com.seckill.service.*;
import com.seckill.util.Verify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = Const.CROSS_ORIGIN, allowCredentials = "true")
public class CustomerController {
    private final TokenService tokenService;
    private final CustomerService customerService;
    private final PreliminaryScreeningService preliminaryScreeningService;
    private final SeckillApplicationFormService seckillApplicationFormService;
    private final SeckillActivityService seckillActivityService;
    private final StorageService storageService;

    /**
     * 注册（提供真名，身份证，密码）
     */
    @PostMapping("/register")
    public synchronized R register(@RequestBody Customer customer) {
        log.info("请求: {}", customer);

        if (Verify.nullOrEmpty(customer.getName(), customer.getIdNumber(), customer.getPassword())) {
            return R.error()
                    .msg(MsgMapping.NAME_ID_NUM_PWD_NULL_OR_EMPTY);
        }

        // 检查身份证号
        if (!Verify.validIdNum(customer.getIdNumber())) {
            return R.error()
                    .msg(MsgMapping.ID_NUM_FORMAT_ERROR);
        }

        if (!customerService.insert(customer)) {
            return R.error()
                    .msg(MsgMapping.ID_NUM_REPEATED);
        }

        log.info("客户注册：{}", customer);

        return R.ok()
                .msg(MsgMapping.REG_SUCCESS)
                .data(Map.of("accountId", customer.getAccountId()));
    }

    /**
     * 登录（提供账号/身份证号和密码） <br>
     * 并根据初筛结果插入到{@link PreliminaryScreening}（如果已经有了就不再插入） <br>
     * 返回token给客户端（token用{uuid} -> {accountName}存进redis）
     */
    @PostMapping("/login")
    public synchronized R login(@CookieValue(value = "token", required = false) String repeatedToken, @RequestBody Customer customer, HttpServletResponse resp) {
        // 防止重复登录
        if (repeatedToken != null && tokenService.getAccountId(repeatedToken) != null) {
            log.info("重复登录：token = {}", repeatedToken);
            return R.error()
                    .msg(MsgMapping.LOGIN_REPEAT);
        }

        log.info("请求: {}", customer);

        // 账号密码是否格式错误
        var formatError = Verify.nullOrEmpty(customer.getAccountId(), customer.getPassword()) &&
                Verify.nullOrEmpty(customer.getIdNumber(), customer.getPassword());

        // 检查输入格式是否正确
        if (formatError) {
            return R.error()
                    .msg(MsgMapping.ACCOUNT_PWD_NULL_OR_EMPTY);
        }

        // 检查账号和密码
        if (!customerService.contains(customer)) {
            return R.error()
                    .msg(MsgMapping.ACCOUNT_OR_PWD_ERROR);
        }

        var token = tokenService.create(customer.getAccountId());

        // 存入客户端cookie
        resp.addCookie(new Cookie("token", token));

        log.info("客户登录：{}", customer);

        // 返回token给客户端
        return R.ok()
                .msg(MsgMapping.LOGIN_SUCCESS);
    }

    @PostMapping("/getCustomerInfo")
    public synchronized R getCustomerInfo(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if (token == null || (customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON);
        }

        var customer = customerService.getByAccountId(customerId);
        var canApply = customerService.canApply(customerId);
        var applied = seckillApplicationFormService.contains(customerId);

        // 往初筛表插入记录
        preliminaryScreeningService.insert(customerId);

        log.info("用户 {} 是否申请过秒杀活动：{}, 是否能够申请：{}", customer.getName(), applied, canApply);

        return R.ok()
                .data(Map.of(
                        "customerName", customer.getName(),
                        "applied", applied,
                        "canApply", canApply
                ));
    }

    /**
     * 申请秒杀 <br>
     */
    @PostMapping("/applyForSeckill")
    public synchronized R applyForSeckill(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if (token == null || (customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON);
        }

        if (seckillApplicationFormService.contains(customerId)) {
            return R.error()
                    .msg(MsgMapping.APPLY_REPEAT);
        }

        var insertSuccess = seckillApplicationFormService.insert(customerId);
        return insertSuccess ? R.ok().msg(MsgMapping.APPLY_SUCCESS) : R.error().msg(MsgMapping.APPLY_FAILED);
    }

    /**
     * 秒杀接口
     * @return 成功的话返回订单
     */
    @PostMapping("/seckill")
    public R seckill(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if (token == null || (customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON);
        }

        var applied = seckillApplicationFormService.contains(customerId);

        if (!applied) {
            return R.error()
                    .msg(MsgMapping.DOES_NOT_APPLY);
        }

        int seckillId = seckillActivityService.getLatestSeckillId();
        int storage = storageService.getStorage(seckillId);



        return null;
    }
}
