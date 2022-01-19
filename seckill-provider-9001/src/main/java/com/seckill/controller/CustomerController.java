package com.seckill.controller;

import com.jmc.net.R;
import com.seckill.pojo.Customer;
import com.seckill.pojo.CustomerInfo;
import com.seckill.pojo.PreliminaryScreening;
import com.seckill.service.CustomerInfoService;
import com.seckill.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jmc
 */
@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    /**
     * 注册（提供真名，身份证，密码）
     */
    @PostMapping("/register")
    public R register(Customer customer) {
        return null;
    }

    /**
     * 登录（提供真名和密码） <br>
     * 并根据初筛结果插入到{@link PreliminaryScreening}（如果已经有了就不再插入） <br>
     * 返回token给客户端（token用customer-{uuid} -> customerName存进redis）
     */
    @PostMapping("/login")
    public R login(Customer customer) {
        return null;
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
