package com.xds.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xds.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xds.eduService.entity.frontvo.CourseVo;
import com.xds.eduService.entity.frontvo.CourseWebVo;
import com.xds.eduService.entity.vo.CourseInfoVo;
import com.xds.eduService.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-08-29
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    CoursePublishVo publishCourseInfo(String id);

    void removeCourse(String courseId);

    Map<String, Object> getCourseFrontList(Page<EduCourse> coursePage, CourseVo courseVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
