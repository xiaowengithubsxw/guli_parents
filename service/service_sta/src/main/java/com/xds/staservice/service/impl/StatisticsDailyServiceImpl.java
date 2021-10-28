package com.xds.staservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xds.commonutils.R;
import com.xds.staservice.client.UcenterClient;
import com.xds.staservice.entity.StatisticsDaily;
import com.xds.staservice.mapper.StatisticsDailyMapper;
import com.xds.staservice.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-23
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;


    //统计某天的注册人数
    @Override
    public void registerCount(String day) {
        //添加记录之前删除表相同的日期的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);
        baseMapper.delete(wrapper);
        //远程调用得到某一天的注册人数
        R registerR = ucenterClient.countRegister(day);
        Integer countRegister = (Integer) registerR.getData().get("countRegister");
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(countRegister);
        statisticsDaily.setDateCalculated(day);
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100, 200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100, 200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100, 200));
        baseMapper.insert(statisticsDaily);


    }

    //图标显示，x坐标，y坐标
    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        //根据条件查询对应数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated", begin, end);
        wrapper.select("date_calculated", type);
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);
        //日期list集合
        List<String> dateList = new ArrayList<>();
        //数量list
        List<Integer> numberList = new ArrayList<>();
        //遍历查询所有数据的list集合遍历 然后分别封装
        for (int i = 0; i < staList.size(); i++) {
            StatisticsDaily daily = staList.get(i);
            //封装日期
            dateList.add(daily.getDateCalculated());
            //封装数量
            switch (type) {
                case "login_num":
                    numberList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numberList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numberList.add(daily.getVideoViewNum());
                    break;
                default:
                    numberList.add(daily.getCourseNum());
                    break;
            }

        }
        Map<String,Object> map=new HashMap<>();
        map.put("dateList",dateList);
        map.put("numberList",numberList);
        return map;
    }
}
