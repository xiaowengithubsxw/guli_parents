package com.xds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author Bai Xu
 * @Date 2021/9/23 15:49
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("com\\xds\\staservice\\mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2
@EnableScheduling//定时任务
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
