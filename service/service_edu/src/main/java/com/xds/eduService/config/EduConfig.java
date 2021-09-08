package com.xds.eduService.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Bai Xu
 * @Date 2021/8/22 13:42
 * @Version 1.0
 */
@Configuration
@MapperScan("com.xds.eduService.mapper")
public class EduConfig {
    /**
     * 逻辑删除插件
     */
    @Bean
    public ISqlInjector sqlInjector(){
    return     new LogicSqlInjector();
    }
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
