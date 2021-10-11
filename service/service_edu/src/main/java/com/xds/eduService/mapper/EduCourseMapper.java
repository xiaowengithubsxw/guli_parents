package com.xds.eduService.mapper;

import com.xds.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xds.eduService.entity.frontvo.CourseWebVo;
import com.xds.eduService.entity.vo.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-08-29
 */

public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo getPublishCourseInfo(String courseId);
    //根据课程id查询课程详细信息
    CourseWebVo getBaseCourseIno(String courseId);
}
