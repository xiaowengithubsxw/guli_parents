package com.xds.staservice.client;

import com.xds.commonutils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author Bai Xu
 * @Date 2021/9/23 16:04
 * @Version 1.0
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    @Autowired

    //查询某一天的注册人数
    @GetMapping("/educenter/member/countRegister/{day}")
    R countRegister(@PathVariable("day")String day);
}
