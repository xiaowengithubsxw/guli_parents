package com.xds.vod.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author Bai Xu
 * @Date 2021/9/6 17:46
 * @Version 1.0
 */
@Service
public interface VodService {
    String uploadVideoALY(MultipartFile file);
}
