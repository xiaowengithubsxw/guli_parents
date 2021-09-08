package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Bai Xu
 * @Date 2021/8/22 13:37
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.xds"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
