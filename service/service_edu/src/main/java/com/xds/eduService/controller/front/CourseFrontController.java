package com.xds.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xds.commonutils.R;
import com.xds.eduService.entity.EduCourse;
import com.xds.eduService.entity.EduTeacher;
import com.xds.eduService.entity.chapter.ChapterVo;
import com.xds.eduService.entity.frontvo.CourseVo;
import com.xds.eduService.entity.frontvo.CourseWebVo;
import com.xds.eduService.service.EduChapterService;
import com.xds.eduService.service.EduCourseService;
import com.xds.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author Bai Xu
 * @Date 2021/9/17 8:59
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduService/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduChapterService chapterService;
    /**
     * 条件查询带分页的功能
     */
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable("page")long page,
                                @PathVariable("limit")long limit,
                                @RequestBody(required = false) CourseVo courseVo){
        Page<EduCourse> coursePage=new Page<>(page,limit);
     Map<String,Object> map=  courseService.getCourseFrontList(coursePage,courseVo);

        return R.ok().data(map);
    }
    //课程详情的方法
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable("courseId") String courseId){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo= courseService.getBaseCourseInfo(courseId);
        //根据课程id查询章节和小节
        List<ChapterVo> chapterVos = chapterService.getChapterVideoByCourseId(courseId);
        System.out.println("11111");
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVos",chapterVos);


    }
    //根据课程名查询课程搜索
//    @GetMapping("getCourseByName/{courseId}")



}
