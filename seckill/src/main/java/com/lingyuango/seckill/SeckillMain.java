package com.lingyuango.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 秒杀模块
 * @author Jmc
 */
@SpringBootApplication
@EnableFeignClients
public class SeckillMain {
    public static void main(String[] args) {
        SpringApplication.run(SeckillMain.class, args);
    }
}
