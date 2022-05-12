package com.lingyuango.seckill.account.controller;

import com.jmc.lang.Objs;
import com.jmc.net.R;
import com.lingyuango.seckill.account.client.MockCreditClient;
import com.lingyuango.seckill.account.common.MsgMapping;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.pojo.MockCreditInfo;
import com.lingyuango.seckill.account.pojo.PreScreening;
import com.lingyuango.seckill.account.service.CustomerInfoService;
import com.lingyuango.seckill.account.service.PreScreeningService;
import com.lingyuango.seckill.account.client.SeckillApplicationFormClient;
import com.lingyuango.seckill.account.service.TokenService;
import com.lingyuango.seckill.account.util.Verify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import com.lingyuango.seckill.account.service.CustomerService;

import javax.servlet.http.HttpServletResponse;
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
    private final CustomerInfoService customerInfoService;
    private final PreScreeningService preScreeningService;
    private final SeckillApplicationFormClient seckillApplicationFormClient;
    private final MockCreditClient mockCreditClient;

    /**
     * 通过账户id获取客户
     */
    @PostMapping("/getCustomer")
    public R<Customer> getCustomer(Integer accountId) {
        return R.ok()
                .data(customerService.getByAccountId(accountId));
    }

    /**
     * 用户是否能申请活动
     */
    @PostMapping("/canApply")
    public R<Boolean> canApply(Integer customerId) {
        return R.ok()
                .data(customerService.canApply(customerId));
    }

    /**
     * 注册（提供真名，身份证，密码）
     */
    @PostMapping("/register")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public synchronized R<Map<String, Integer>> register(@RequestBody Customer customer) {
        log.info("请求: {}", customer);

        if (Objs.nullOrEmpty(customer.getName(), customer.getIdNumber(), customer.getPassword())) {
            return R.error()
                    .msg(MsgMapping.NAME_ID_NUM_PWD_NULL_OR_EMPTY)
                    .build();
        }

        // 检查身份证号
        if (!Verify.validIdNum(customer.getIdNumber())) {
            return R.error()
                    .msg(MsgMapping.ID_NUM_FORMAT_ERROR)
                    .build();
        }

        if (!customerService.insert(customer)) {
            return R.error()
                    .msg(MsgMapping.ID_NUM_REPEATED)
                    .build();
        }

        // 插入模拟的用户初筛信息
        var mockCreditInfo = mockCreditClient.getCreditInfo().get();
        if (!customerInfoService.insert(customer, mockCreditInfo)) {
            return R.error()
                    .msg(MsgMapping.USER_PRE_SCREEN_INFO_CREATE_ERROR)
                    .build();
        }

        log.info("客户注册：{}", customer);

        return R.ok()
                .msg(MsgMapping.REG_SUCCESS)
                .data(Map.of("accountId", customer.getAccountId()));
    }

    /**
     * 登录（提供账号/身份证号和密码） <br>
     * 并根据初筛结果插入到{@link PreScreening}（如果已经有了就不再插入） <br>
     * 返回token给客户端（token用{token:uuid} -> {accountId}存进redis）
     */
    @PostMapping("/login")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public synchronized R<Void> login(@CookieValue(value = "token", required = false) String repeatedToken,
                                @RequestBody Customer customer,
                                HttpServletResponse resp) {
        // 防止重复登录
        if (tokenService.getAccountId(repeatedToken) != null) {
            log.info("重复登录：token = {}", repeatedToken);
            return R.error()
                    .msg(MsgMapping.LOGIN_REPEAT)
                    .build();
        }

        log.info("请求: {}", customer);

        // 账号密码是否格式错误
        var formatError = Objs.nullOrEmpty(customer.getAccountId(), customer.getPassword()) &&
                Objs.nullOrEmpty(customer.getIdNumber(), customer.getPassword());

        // 检查输入格式是否正确
        if (formatError) {
            return R.error()
                    .msg(MsgMapping.ACCOUNT_PWD_NULL_OR_EMPTY)
                    .build();
        }

        // 检查账号和密码
        if (!customerService.contains(customer)) {
            return R.error()
                    .msg(MsgMapping.ACCOUNT_OR_PWD_ERROR)
                    .build();
        }

        // 获取token
        var token = tokenService.create(customer.getAccountId());

        resp.addCookie(token);

        log.info("客户登录：{}", customer);

        // 返回token给客户端
        return R.ok()
                .msg(MsgMapping.LOGIN_SUCCESS)
                .build();
    }

    @PostMapping("/getInfo")
    @CrossOrigin(originPatterns = "*", allowCredentials = "true")
    public synchronized R<Map<String, Object>> getInfo(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error()
                    .msg(MsgMapping.NOT_LOGGED_ON)
                    .build();
        }

        var customer = customerService.getByAccountId(customerId);
        var canApply = customerService.canApply(customerId);
        var applied = seckillApplicationFormClient.contains(customerId).get();

        // 往初筛表插入记录
        preScreeningService.insert(customerId);

        log.info("用户 {} 是否申请过秒杀活动：{}, 是否能够申请：{}", customer.getName(), applied, canApply);

        return R.ok()
                .data(Map.of(
                        "name", customer.getName(),
                        "applied", applied,
                        "canApply", canApply
                ));
    }
}
