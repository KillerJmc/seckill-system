package com.lingyuango.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Jmc
 */
@SpringBootApplication
@EnableFeignClients
public class SeckillServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(SeckillServiceMain.class, args);
    }
}
