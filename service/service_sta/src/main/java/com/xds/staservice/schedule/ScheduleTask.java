package com.xds.staservice.schedule;

import com.xds.staservice.service.StatisticsDailyService;
import com.xds.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author Bai Xu
 * @Date 2021/9/23 18:52
 * @Version 1.0
 */
@Component
public class ScheduleTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;
   // @Scheduled(cron = "0/5 * * * * ?")//每隔5秒执行一次
    public void task1(){
        System.out.println("*********task1执行了...");
    }
    @Scheduled(cron = "0 0 9 * * ?")//每天凌晨一点执行,把前一天的数据查询添加
    public void task2(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }

}
