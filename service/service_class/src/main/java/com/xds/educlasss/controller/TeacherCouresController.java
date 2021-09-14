package com.xds.educlasss.controller;


import com.xds.commonutils.R;
import com.xds.educlasss.entity.TeacherCoures;
import com.xds.educlasss.service.TeacherCouresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-14
 */
@RestController
@RequestMapping("/educlass/coures")
@CrossOrigin
public class TeacherCouresController {
    @Autowired
    private TeacherCouresService teacherCouresService;
    @GetMapping("getAll")
    public R getAll(){
        List<TeacherCoures> list = teacherCouresService.list(null);
        return R.ok().data("list",list);
    }

}

