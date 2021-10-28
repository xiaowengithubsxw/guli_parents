package com.xds.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xds.commonutils.R;
import com.xds.eduService.entity.EduCourse;
import com.xds.eduService.entity.EduTeacher;
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
@RequestMapping("/eduService/teacherfront")
@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;
    @Autowired
    private EduCourseService courseService;

    /**
     * 分页查询讲师
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable("page")long page,
                                 @PathVariable("limit")long limit){
       Page<EduTeacher> teacherPage = new Page<>(page,limit);
        Map<String,Object> map=teacherService.getTeacherFrontList(teacherPage);
        //返回分页的所有数据 不用框架
        return R.ok().data(map);
    }
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable("teacherId")String teacherId){
        //根据id查询讲师基本信息
        EduTeacher teacher = teacherService.getById(teacherId);
        //根据id查询讲师所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourse> courses = courseService.list(wrapper);
//        System.out.println("111111111111111111111111111111111111111");
        return R.ok().data("teacher",teacher).data("courseList",courses);
    }


}
