package com.lingyuango.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * RocketMQ序列化 VM Options: --add-opens java.base/java.lang.invoke=ALL-UNNAMED
 * @author Jmc
 */
@SpringBootApplication
@EnableFeignClients
public class SeckillServiceMain {
    public static void main(String[] args) {
        SpringApplication.run(SeckillServiceMain.class, args);
    }
}
