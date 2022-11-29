package com.lingyuango.seckillmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.erupt.core.annotation.EruptScan;

/**
 * A22秒杀后台管理系统启动类 <br>
 * @author lmccc1, ChaconneLuo, Jmc
 */
@SpringBootApplication
@EruptScan
public class ManagementMain {
	public static void main(String[] args) {
		SpringApplication.run(ManagementMain.class, args);
	}
}
