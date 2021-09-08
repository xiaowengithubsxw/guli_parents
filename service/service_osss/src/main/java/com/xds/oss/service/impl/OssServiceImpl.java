package com.xds.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.xds.oss.service.OssService;
import com.xds.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author Bai Xu
 * @Date 2021/8/27 11:46
 * @Version 1.0
 */
@Component
public class OssServiceImpl implements OssService {
    /**
     * 上传文件到oss
     * @param file
     * @return
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName=ConstantPropertiesUtils.BUCKET_NAME;


        try {
            //创建oss实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            String filename = file.getOriginalFilename();
            String uuid= UUID.randomUUID().toString().replaceAll("-", "");
            filename=uuid+filename;
            //把文件按照日期进行分类
            String dataPath = new DateTime().toString("yyyy/MM/dd");
            filename=dataPath+"/"+filename;
            ossClient.putObject(bucketName, filename, inputStream);
            ossClient.shutdown();
            String url="https://"+bucketName+"."+endpoint+"/"+filename;
            return url;

       } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
