package com.lingyuango.seckill.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 账号模块
 * @author Jmc
 */
@SpringBootApplication
@EnableFeignClients
public class AccountMain {
    public static void main(String[] args) {
        SpringApplication.run(AccountMain.class, args);
    }
}
