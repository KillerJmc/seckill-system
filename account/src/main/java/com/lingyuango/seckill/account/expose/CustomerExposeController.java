package com.lingyuango.seckill.account.expose;

import com.jmc.net.R;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expose/customer")
public class CustomerExposeController {
    private final CustomerService customerService;

    /**
     * 通过账号获取客户对象
     */
    @GetMapping("/getByAccount")
    public R<Customer> getByAccount(Integer account) {
        return R.ok(customerService.getByAccount(account));
    }

    /**
     * 用户是否能申请活动
     */
    @GetMapping("/canApply")
    public R<Boolean> canApply(Integer account) {
        return R.ok(customerService.canApply(account));
    }
}
