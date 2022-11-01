package com.lingyuango.seckill.account.expose;

import com.jmc.net.R;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/expose/customer")
public class CustomerExposeController {
    private final CustomerService customerService;

    /**
     * 通过账户id获取客户
     */
    @PostMapping("/getCustomer")
    public R<Customer> getCustomer(Integer accountId) {
        return R.ok(customerService.getByAccountId(accountId));
    }

    /**
     * 用户是否能申请活动
     */
    @PostMapping("/canApply")
    public R<Boolean> canApply(Integer customerId) {
        return R.ok(customerService.canApply(customerId));
    }
}
