package com.seckill.controller;

import com.jmc.net.R;
import com.seckill.common.Const;
import com.seckill.common.MsgMapping;
import com.seckill.pojo.Customer;
import com.seckill.pojo.PreliminaryScreening;
import com.seckill.service.CustomerService;
import com.seckill.util.Verify;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final StringRedisTemplate redisTemplate = new StringRedisTemplate();

    private final CustomerService customerService;

    /**
     * 注册（提供真名，身份证，密码）
     */
    @PostMapping("/register")
    public synchronized R register(Customer customer) {
        if (Verify.nullOrEmpty(customer.getName(), customer.getIdNumber(), customer.getPassword())) {
            return R.error()
                    .msg(MsgMapping.ACCOUNT_ID_NUM_PWD_NULL_OR_EMPTY);
        }

        if (!customerService.insert(customer)) {
            return R.error()
                    .msg(MsgMapping.ID_NUM_REPEATED);
        }

        return R.ok()
                .msg(MsgMapping.REG_SUCCESS);
    }

    /**
     * 登录（提供账号和密码） <br>
     * 并根据初筛结果插入到{@link PreliminaryScreening}（如果已经有了就不再插入） <br>
     * 返回token给客户端（token用customer-{uuid} -> customerName存进redis）
     */
    @PostMapping("/login")
    public R login(Customer customer) {
        if (Verify.nullOrEmpty(customer.getAccountId(), customer.getPassword())) {
            return R.error()
                    .msg(MsgMapping.ACCOUNT_PWD_NULL_OR_EMPTY);
        }

        if (!customerService.contains(customer)) {
            return R.error()
                    .msg(MsgMapping.ACCOUNT_OR_PWD_ERROR);
        }

        // TODO: 初筛结果

        // 定义token
        var token = Const.CUSTOMER_TOKEN_PREFIX + UUID.randomUUID();

        // 存入redis
        redisTemplate.opsForValue().set(token, customer.getName());

        // 返回token给客户端
        return R.ok()
                .msg(MsgMapping.LOGIN_SUCCESS)
                .data(token);
    }

    /**
     * 申请秒杀 <br>
     */
    @PostMapping("/applyForSeckill")
    public R applyForSeckill(String token, Integer seckillId) {
        return null;
    }

    /**
     * 秒杀接口
     * @param url 秒杀随机地址
     * @return 成功的话返回订单
     */
    @PostMapping("/seckill/{url}")
    public R seckill(String token, @PathVariable String url) {
        return null;
    }
}
