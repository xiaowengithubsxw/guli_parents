package com.xds.eduService.controller;


import com.xds.commonutils.R;
import com.xds.eduService.client.VodClient;
import com.xds.eduService.entity.EduVideo;
import com.xds.eduService.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-29
 */
@RestController
@RequestMapping("/eduService/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;
    //注入vodClient
    @Autowired
    private VodClient vodClient;

    /**
     * 添加小节
     * @param eduVideo
     * @return
     */
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    /**
     * 删除小节 同时把阿里云视屏删除 通过调用vod中的方法 实现微服务调用
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        //根据小节id获取视频id，调用方法实现视频的删除
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if (StringUtils.hasLength(videoSourceId)){
            vodClient.removeALYVideo(videoSourceId);
        }
        //删除小节
        eduVideoService.removeById(id);
        return R.ok();
    }
}

