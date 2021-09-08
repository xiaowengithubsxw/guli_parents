package com.xds.vodtest;


import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.*;

import java.util.List;

/**
 * @Author Bai Xu
 * @Date 2021/9/6 13:45
 * @Version 1.0
 */
public class TestVod {
    public static void main(String[] args) {
        String accessKeyId = "LTAI5tM7EdF2gnXr4WeJ5fbm";
        String accessKeySecret = "OlwuJS11TbYK7gxQJ0SNM5PdGduwwe";

        String title = "sss.MP4";   //上传之后文件名称
        String fileName = "D:\\BaiduNetdiskDownload\\vod\\sss.mp4";  //本地文件路径和名称
        //上传视频的方法
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    /**
     * 根据视频id获取视频播放地址
     * @throws ClientException
     */
    public static void getPlayUrl() throws ClientException {
        //1.根据视频id获取视频播放地址 播放不了加密的
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tM7EdF2gnXr4WeJ5fbm", "OlwuJS11TbYK7gxQJ0SNM5PdGduwwe");
        //创建获取视频地址request和response
        GetPlayInfoRequest request=new GetPlayInfoRequest();
        GetPlayInfoResponse response=new GetPlayInfoResponse();
        //向request对象里面的方法传递request，获取数据
        request.setVideoId("406f6c5b2664489f9d463e5c79df0102");
        //调用初始化对象里面的方法传递request，获取数据
        response = client.getAcsResponse(request);
        List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
        //播放地址
        for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
            System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
        }
        //Base信息
        System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
    }

    /**
     * 根据视频id获取视频凭证
     * @throws ClientException
     */
    public static void getPlayAuth() throws ClientException {
        //根据视频id获取视频播放凭证
        //创建初始化对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tM7EdF2gnXr4WeJ5fbm", "OlwuJS11TbYK7gxQJ0SNM5PdGduwwe");
        //创建获取视频凭证的request和response
        GetVideoPlayAuthRequest request=new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response=new GetVideoPlayAuthResponse();
        //向request对象设置视频id值
        request.setVideoId("406f6c5b2664489f9d463e5c79df0102");
        //调用初始化对象方法得到视频凭证
        response= client.getAcsResponse(request);
        System.out.println("playauth"+response.getPlayAuth());

    }
}
