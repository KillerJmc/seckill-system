package com.lingyuango.seckill.account.controller;

import com.jmc.lang.Objs;
import com.jmc.net.R;
import com.lingyuango.seckill.account.client.SeckillApplicationFormClient;
import com.lingyuango.seckill.account.common.MsgMapping;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.service.CustomerService;
import com.lingyuango.seckill.account.service.PreScreeningService;
import com.lingyuango.seckill.account.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    private final TokenService tokenService;
    private final CustomerService customerService;
    private final PreScreeningService preScreeningService;
    private final SeckillApplicationFormClient seckillApplicationFormClient;

    /**
     * 注册（提供真名，身份证，密码）
     */
    @PostMapping("/register")
    public R<Map<String, Integer>> register(@RequestBody Customer customer) {
        // 客户对象是否为空
        var emptyCheck = Objs.nullOrEmpty(customer.getName(), customer.getIdNumber(), customer.getPassword());

        return R.stream()
                // 非空检查
                .check(emptyCheck, MsgMapping.NAME_ID_NUM_PWD_NULL_OR_EMPTY)
                // 检查身份证号
                .check(() -> customerService.checkIdNum(customer.getIdNumber()))
                // 将客户对象放入数据库
                .exec(() -> customerService.insert(customer))
                // 返回客户账号
                .build(() -> Map.of("account", customer.getAccount()));
    }

    /**
     * 登录（提供账号/身份证号和密码）
     */
    @PostMapping("/login")
    public R<Void> login(@CookieValue(value = "token", required = false) String repeatedToken,
                        @RequestBody Customer customer,
                        HttpServletRequest req,
                        HttpServletResponse resp) {
        // 账号密码是否格式错误
        var formatError = Objs.nullOrEmpty(customer.getAccount(), customer.getPassword()) &&
                Objs.nullOrEmpty(customer.getIdNumber(), customer.getPassword());

        return R.stream()
                // 重复登录检查
                .check(tokenService.getAccount(repeatedToken) != null, MsgMapping.LOGIN_REPEAT)
                // 账号密码格式检查
                .check(formatError, MsgMapping.ACCOUNT_PWD_NULL_OR_EMPTY)
                // 检查账号密码正确
                .check(!customerService.contains(customer), MsgMapping.ACCOUNT_OR_PWD_ERROR)
                // 创建token并设置cookie
                .exec(() -> tokenService.addLoginCookies(customer.getAccount(), req, resp))
                .exec(() -> log.info("客户登录：{}", customer))
                .build();
    }

    /**
     * 退出登录
     * @param token 登录凭证
     * @param req 请求体对象
     * @param resp 响应体对象
     */
    @PostMapping("/logout")
    public R<Void> logout(@CookieValue(value = "token", required = false) String token,
                          HttpServletRequest req,
                          HttpServletResponse resp) {
        return R.stream()
                // 删除token
                .exec(() -> tokenService.deleteLoginCookies(token, req, resp))
                .build();
    }

    /**
     * 获取客户账户信息
     * @param account 客户账号
     * @return 具体信息
     */
    @GetMapping("/getInfo")
    public R<Map<String, Object>> getInfo(@CookieValue("account") Integer account) {
        var customer = customerService.getByAccount(account);
        var canApply = customerService.canApply(account);
        var applied = seckillApplicationFormClient.contains(account).getData();

        return R.stream()
                // 往初筛表插入记录
                .exec(() -> preScreeningService.insert(account))
                .build(Map.of(
                        "name", customer.getName(),
                        "applied", applied,
                        "canApply", canApply
                ));
    }
}
