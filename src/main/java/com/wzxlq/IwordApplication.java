package com.wzxlq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//配置开启mybatis扫码dao包的注解
@MapperScan("com.wzxlq.dao")
//配置开启定时任务的注解
@EnableScheduling
@EnableTransactionManagement(proxyTargetClass = true)
public class IwordApplication {

    public static void main(String[] args) {
        SpringApplication.run(IwordApplication.class, args);
    }

}
