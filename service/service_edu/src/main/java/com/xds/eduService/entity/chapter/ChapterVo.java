package com.xds.eduService.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Bai Xu
 * @Date 2021/9/4 15:42
 * @Version 1.0
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    //表示小节
    private List<VideoVo> children=new ArrayList<>();
}
