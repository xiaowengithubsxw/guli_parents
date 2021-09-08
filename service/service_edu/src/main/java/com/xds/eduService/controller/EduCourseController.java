package com.xds.eduService.controller;


import com.xds.commonutils.R;
import com.xds.eduService.entity.EduCourse;
import com.xds.eduService.entity.vo.CourseInfoVo;
import com.xds.eduService.entity.vo.CoursePublishVo;
import com.xds.eduService.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-29
 */
@RestController
@RequestMapping("/eduService/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    EduCourseService eduCourseService;
    /**
     * 添加课程基本信息的方法
     */
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        //返回添加之后课程id，为了后面添加大纲使用
     String id=   eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("id", id);
    }

    /**
     * 根据课程id进行数据回显
     * @param courseId
     * @return
     */
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId){
     CourseInfoVo courseInfoVo=   eduCourseService.getCourseInfo(courseId);
     return R.ok().data("courseInfoVo",courseInfoVo);
    }

    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 课程确认信息
     * @param id
     * @return
     */
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id){
     CoursePublishVo coursePublishVo= eduCourseService.publishCourseInfo(id);
     return R.ok().data("publishCourse", coursePublishVo);
    }

    /**
     * 课程最终发布 修改课程状态
     * @param id
     * @return
     */
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id){
        EduCourse eduCourse=new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }

    /**
     * 课程列表
     * @return
     */
    @GetMapping()
    public R getCourseList(){
        List<EduCourse> list = eduCourseService.list(null);
        return R.ok().data("list", list);
    }

    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        eduCourseService.removeCourse(courseId);
        return R.ok();
    }
}

