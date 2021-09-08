package com.xds.oss.utils;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Bai Xu
 * @Date 2021/8/27 11:29
 * @Version 1.0
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {
    //读取配置内容
    @Value("${aliyun.oss.file.endpoint}")
    private  String endpoint;//地域节点
    @Value("${aliyun.oss.file.keyid}")
    private  String keyId ;//oss id
    @Value("${aliyun.oss.file.keysecret}")
    private  String keySecret ;//oss password
    @Value("${aliyun.oss.file.bucketname}")
    private  String bucketName; //oss name
    //定义公开常量
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endpoint;
        ACCESS_KEY_ID=keyId;
        ACCESS_KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;
    }
}
