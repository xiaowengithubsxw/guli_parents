package com.xds.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Bai Xu
 * @Date 2021/9/15 9:25
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.xds")
@MapperScan("com\\xds\\educms\\mapper")
public class CsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(CsmApplication.class, args);
    }
}
