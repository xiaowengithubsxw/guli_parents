package com.xds.oss.controller;

import com.xds.commonutils.R;
import com.xds.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Bai Xu
 * @Date 2021/8/27 11:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
@Api(tags = "上传")
public class OssController {
    @Autowired
    OssService ossService;
    @PostMapping
    @ApiOperation(value = "上传")
    public R uploadFile(MultipartFile file){
     String url= ossService.uploadFileAvatar(file);
     return R.ok().data("url",url);

    }

}
