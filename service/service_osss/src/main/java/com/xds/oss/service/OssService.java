package com.xds.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Bai Xu
 * @Date 2021/8/27 11:45
 * @Version 1.0
 */
@Service
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
