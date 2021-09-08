package com.xds.eduService.entity.vo;

import lombok.Data;

/**
 * @Author Bai Xu
 * @Date 2021/9/5 17:26
 * @Version 1.0
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
