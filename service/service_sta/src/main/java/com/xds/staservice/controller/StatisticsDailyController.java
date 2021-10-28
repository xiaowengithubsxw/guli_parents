package com.xds.staservice.controller;


import com.xds.commonutils.R;
import com.xds.staservice.service.StatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-23
 */
@RestController
@RequestMapping("/staservice/sta")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;
    //统计某一天的注册人数
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable("day")String day){
        statisticsDailyService.registerCount(day);
        return R.ok();
    }
    //图标显示返回两部分数据，日期json数组，数量的json数据
    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable("type")String type,
                     @PathVariable("begin")String begin,
                      @PathVariable("end")String end){
     Map<String,Object> map= statisticsDailyService.getShowData(type,begin,end);
     return R.ok().data(map);

    }
}

