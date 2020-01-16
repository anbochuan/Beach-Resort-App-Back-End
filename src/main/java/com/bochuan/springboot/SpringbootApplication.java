package com.bochuan.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RestController;


// @SpringBootApplication：申明让spring boot自动给程序进行必要的配置，
// 这个配置等同于：@Configuration ，@EnableAutoConfiguration 和 @ComponentScan 三个配置。
@SpringBootApplication
//@ServletComponentScan
public class SpringbootApplication {

//	@Bean
//	public BCryptPasswordEncoder bCryptPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}


