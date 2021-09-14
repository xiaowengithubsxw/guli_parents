package com.xds;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author Bai Xu
 * @Date 2021/9/13 15:32
 * @Version 1.0
 */
@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
@MapperScan("com\\xds\\educlasss\\mapper")

public class ClassApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClassApplication.class, args);
    }
}
