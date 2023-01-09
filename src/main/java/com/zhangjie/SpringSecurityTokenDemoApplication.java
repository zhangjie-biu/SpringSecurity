package com.zhangjie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhangjie.mapper")
public class SpringSecurityTokenDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityTokenDemoApplication.class, args);
	}

}
