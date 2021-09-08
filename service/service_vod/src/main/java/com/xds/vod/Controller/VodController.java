package com.xds.vod.Controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.xds.commonutils.R;
import com.xds.vod.Utils.ConstantVodUtils;
import com.xds.vod.Utils.InitVodClient;
import com.xds.vod.exception.GuliException;
import com.xds.vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Bai Xu
 * @Date 2021/9/6 17:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;
    /**
     * 上传视频到阿里云
     * @return
     */
    @PostMapping("uploadALiYunVideo")
    public R uploadALiYunVideo(MultipartFile file){
        //返回上传视频id值
        String videoId= vodService.uploadVideoALY(file);
        return R.ok().data("videoId", videoId);
    }
    /**
     * 根据视频id删除阿里云视频
     */
    @DeleteMapping("removeALYVideo/{id}")
    public R removeALYVideo(@PathVariable String id){

        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频的request对象
            DeleteVideoRequest request=new DeleteVideoRequest();
            //向request对象设置视频id
            request.setVideoIds(id);
            //调用初始化对象方法实现删除
            client.getAcsResponse(request);
            return R.ok();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败");
        }
    }



}
