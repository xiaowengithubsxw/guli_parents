package com.xds.eduService.client;

import com.xds.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author Bai Xu
 * @Date 2021/9/12 16:58
 * @Version 1.0
 */
@FeignClient("service-vod")
@Component
public interface VodClient {
    //定义调用方法路径
    /**
     * 根据视频id删除阿里云视频
     */
    @DeleteMapping(value = "/eduvod/video/removeALYVideo/{id}")
    public R removeALYVideo(@PathVariable("id") String id);
}
