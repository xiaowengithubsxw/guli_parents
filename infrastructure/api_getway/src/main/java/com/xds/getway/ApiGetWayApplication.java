package com.xds.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author Bai Xu
 * @Date 2021/9/25 8:49
 * @Version 1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ApiGetWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGetWayApplication.class, args);
    }
}
