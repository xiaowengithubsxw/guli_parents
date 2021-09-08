package com.xds.eduService.controller;


import com.xds.commonutils.R;
import com.xds.eduService.entity.subject.OneSubject;
import com.xds.eduService.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-28
 */
@RestController
@RequestMapping("/eduService/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    /**
     * 添加课程分类
     * @param file
     * @return
     */
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //获取上传过来的excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    /**
     * 课程分类列表
     * @return
     */
    @GetMapping("getAllSubject")
    public R getAllSubject(){
        List<OneSubject> list=subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }

}

