package com.xds.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xds.commonutils.R;
import com.xds.eduService.entity.EduCourse;
import com.xds.eduService.entity.EduTeacher;
import com.xds.eduService.service.EduCourseService;
import com.xds.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Bai Xu
 * @Date 2021/9/15 11:05
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduService/indexFront")
@CrossOrigin
public class IndexFrontController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    EduTeacherService eduTeacherService;
    //查询前8条热门课程 前4条名师
    @GetMapping("index")
    public R index(){
        //查询前8条热门课程
        QueryWrapper<EduCourse> Cwrapper = new QueryWrapper<>();
        Cwrapper.orderByDesc("id");
        Cwrapper.last("limit 8");
        List<EduCourse> courseList = eduCourseService.list(Cwrapper);
        // 前4条名师
        QueryWrapper<EduTeacher> Twrapper = new QueryWrapper<>();
        Twrapper.orderByDesc("id");
        Twrapper.last("limit 4");
        List<EduTeacher> teacherList = eduTeacherService.list(Twrapper);
        return R.ok().data("courseList",courseList).data("teacherList",teacherList);
    }
}
