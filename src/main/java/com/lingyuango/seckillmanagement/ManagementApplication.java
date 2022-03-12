package com.lingyuango.seckillmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.erupt.core.annotation.EruptScan;

import java.awt.*;
import java.net.URI;

/**
 * A22秒杀后台管理系统启动类 <br>
 * VM Options: --add-opens java.base/sun.reflect.annotation=ALL-UNNAMED
 */
@SpringBootApplication
@EruptScan
public class ManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}
}
