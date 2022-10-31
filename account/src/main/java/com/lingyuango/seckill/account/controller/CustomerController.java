package com.lingyuango.seckill.account.controller;

import com.jmc.lang.Objs;
import com.jmc.net.R;
import com.lingyuango.seckill.account.client.MockCreditClient;
import com.lingyuango.seckill.account.client.SeckillApplicationFormClient;
import com.lingyuango.seckill.account.common.MsgMapping;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.pojo.PreScreening;
import com.lingyuango.seckill.account.service.CustomerInfoService;
import com.lingyuango.seckill.account.service.CustomerService;
import com.lingyuango.seckill.account.service.PreScreeningService;
import com.lingyuango.seckill.account.service.TokenService;
import com.lingyuango.seckill.account.util.Verify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
     * 注册（提供真名，身份证，密码）
     */
    @PostMapping("/register")
    public synchronized R<Map<String, Integer>> register(@RequestBody Customer customer) {
        log.info("请求: {}", customer);

        if (Objs.nullOrEmpty(customer.getName(), customer.getIdNumber(), customer.getPassword())) {
            return R.error(MsgMapping.NAME_ID_NUM_PWD_NULL_OR_EMPTY);
        }

        // 检查身份证号
        if (!Verify.validIdNum(customer.getIdNumber())) {
            return R.error(MsgMapping.ID_NUM_FORMAT_ERROR);
        }

        if (!customerService.insert(customer)) {
            return R.error(MsgMapping.ID_NUM_REPEATED);
        }

        // 插入模拟的用户初筛信息
        var mockCreditInfo = mockCreditClient.getCreditInfo().getData();
        if (!customerInfoService.insert(customer, mockCreditInfo)) {
            return R.error(MsgMapping.USER_PRE_SCREEN_INFO_CREATE_ERROR);
        }

        log.info("客户注册：{}", customer);

        return R.ok(Map.of("accountId", customer.getAccountId()));
    }

    /**
     * 登录（提供账号/身份证号和密码） <br>
     * 并根据初筛结果插入到{@link PreScreening}（如果已经有了就不再插入） <br>
     * 返回token给客户端（token用{token:uuid} -> {accountId}存进redis）
     */
    @PostMapping("/login")
    public synchronized R<Void> login(@CookieValue(value = "token", required = false) String repeatedToken,
                                @RequestBody Customer customer,
                                HttpServletResponse resp) {
        // 防止重复登录
        if (tokenService.getAccountId(repeatedToken) != null) {
            log.info("重复登录：token = {}", repeatedToken);
            return R.error(MsgMapping.LOGIN_REPEAT);
        }

        log.info("请求: {}", customer);

        // 账号密码是否格式错误
        var formatError = Objs.nullOrEmpty(customer.getAccountId(), customer.getPassword()) &&
                Objs.nullOrEmpty(customer.getIdNumber(), customer.getPassword());

        // 检查输入格式是否正确
        if (formatError) {
            return R.error(MsgMapping.ACCOUNT_PWD_NULL_OR_EMPTY);
        }

        // 检查账号和密码
        if (!customerService.contains(customer)) {
            return R.error(MsgMapping.ACCOUNT_OR_PWD_ERROR);
        }

        // 获取token
        var token = tokenService.create(customer.getAccountId());

        resp.addCookie(token);

        log.info("客户登录：{}", customer);

        // 返回token给客户端
        return R.ok().build();
    }

    @PostMapping("/getInfo")
    public synchronized R<Map<String, Object>> getInfo(@CookieValue(value = "token", required = false) String token) {
        Integer customerId;
        if ((customerId = tokenService.getAccountId(token)) == null) {
            return R.error(MsgMapping.NOT_LOGGED_ON);
        }

        var customer = customerService.getByAccountId(customerId);
        var canApply = customerService.canApply(customerId);
        var applied = seckillApplicationFormClient.contains(customerId).getData();

        // 往初筛表插入记录
        preScreeningService.insert(customerId);

        log.info("用户 {} 是否申请过秒杀活动：{}, 是否能够申请：{}", customer.getName(), applied, canApply);

        return R.ok(Map.of(
            "name", customer.getName(),
            "applied", applied,
            "canApply", canApply
        ));
    }
}
