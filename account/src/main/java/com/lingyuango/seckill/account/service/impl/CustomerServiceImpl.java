package com.lingyuango.seckill.account.service.impl;

import com.lingyuango.seckill.account.client.SeckillActivityClient;
import com.lingyuango.seckill.account.common.Const;
import com.lingyuango.seckill.account.common.MsgMapping;
import com.lingyuango.seckill.account.dao.CustomerDao;
import com.lingyuango.seckill.account.pojo.Customer;
import com.lingyuango.seckill.account.service.CustomerInfoService;
import com.lingyuango.seckill.account.service.CustomerService;
import com.lingyuango.seckill.account.util.Calculator;
import com.lingyuango.seckill.account.util.Verify;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * @author Jmc
 */
@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;
    private final SeckillActivityClient seckillActivityClient;
    private final CustomerInfoService customerInfoService;

    @Override
    public void insert(Customer customer) throws Exception {
        // 身份证不能重复
        if (customerDao.existsByIdNumber(customer.getIdNumber())) {
            throw new Exception(MsgMapping.ID_NUM_REPEATED);
        }

        // 获取当前最大id
        var maxId = customerDao.getMaxId();
        maxId = maxId == null ? 0 : maxId;

        // 设置新用户id
        customer.setAccount(Const.ACCOUNT_ID_OFFSET + maxId + 1);

        customerDao.save(customer);
        customerInfoService.insert(customer);
        log.info("客户注册：{}", customer);
    }

    @Override
    public boolean contains(Customer customer) {
        return customerDao.findOne(Example.of(customer))
                .map(queryCustomer -> {
                    // 回填信息
                    customer.setAccount(queryCustomer.getAccount());
                    customer.setName(queryCustomer.getName());
                    return queryCustomer;
                })
                .isPresent();
    }

    @Override
    public Customer getByAccount(Integer account) {
        return customerDao.getOneByAccount(account);
    }

    @Override
    public boolean canApply(Integer account) {
        var customer = getByAccount(account);

        var rule = seckillActivityClient.getRule().getData();
        var customerInfo = customerInfoService.getByAccount(account);
        int customerAge = Calculator.getAge(customer.getIdNumber());

        return  customerAge >= rule.getMinAge() && customerAge <= rule.getMaxAge() &&
                customerInfo.getWorkStatus() == rule.getWorkStatus() &&
                customerInfo.getInCreditBlacklist() == rule.getInCreditBlacklist() &&
                customerInfo.getOverdueTimes() <= rule.getMaxOverdueTimes() &&
                customerInfo.getOverdueDays() <= rule.getMaxOverdueDays() &&
                customerInfo.getOverdueMoney() <= rule.getMaxOverdueMoney();
    }

    @Override
    public void checkIdNum(String idNumber) throws Exception {
        if (!Verify.validIdNum(idNumber)) {
            throw new Exception(MsgMapping.ID_NUM_FORMAT_ERROR);
        }
    }
}
