package com.xds.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author Bai Xu
 * @Date 2021/9/16 9:45
 * @Version 1.0
 */
@SpringBootApplication
@ComponentScan("com.xds")
@MapperScan("com\\xds\\educenter\\mapper")
@EnableSwagger2
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
