package com.xds.eduService.controller;





import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xds.commonutils.R;
import com.xds.eduService.entity.EduTeacher;

import com.xds.eduService.entity.vo.TeacherQuery;
import com.xds.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-08-22
 */
@Api(tags = "讲师管理")
@RestController
@RequestMapping("/eduService/teacher")
@CrossOrigin//允许跨域
public class EduTeacherController {
    //1.查询讲师表中的数据
    @Autowired
    private EduTeacherService teacherService;
    @GetMapping("findAll")
    @ApiOperation(value = "所有讲师列表")
    public R findAllTeacher(){
         //调用service的方法实现查询所有操作
        List<EduTeacher> list = teacherService.list(null);
        System.out.println("11111");
        return R.ok().data("items",list);
    }
    @DeleteMapping("{id}")
    @ApiOperation(value = "逻辑删除讲师")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable String id){
        boolean remove = teacherService.removeById(id);
        if (remove){
            return R.ok();
        }else {
           return R.error();
        }
    }
    /**
     * 分页查询讲师的方法
     */
    @GetMapping("pageTeacher/{current}/{limit}")
    @ApiOperation(value = "分页查询讲师")
    public R pageListTeacher(@PathVariable long current,@PathVariable long limit){
        Page<EduTeacher> page = new Page<>(current,limit);
        //手动模拟异常
//        int i=10/0;
       /* try {
            int i=10/0;
        }catch (Exception e){
            throw new GuliException(250,"出错了");
        }*/
        teacherService.page(page, null);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total",total).data("records",records);
    }
    /**
     * 条件查询带分页
     */
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    @ApiOperation(value = "条件带分页查询讲师")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery){
        Page<EduTeacher> page = new Page<>(current,limit);
        //调用方法实现条件查询带分页
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.gt("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_modified", end);
        }
        teacherService.page(page,wrapper);
        long total = page.getTotal();
        List<EduTeacher> records = page.getRecords();
        return R.ok().data("total",total).data("records",records);

    }
    /**
     * 添加讲师模块
      */
   @PostMapping("addTeacher")
   @ApiOperation(value = "添加讲师")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
       boolean save = teacherService.save(eduTeacher);
       if (save){
           return R.ok();
       }else {
           return R.error();
       }
   }
    /**
     * 根据id查询数据回显
     */
    @GetMapping("getTeacher/{id}")
    @ApiOperation(value = "根据id查询数据回显")
    public R getTeacher(@PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }
    /**
     * 讲师修改功能
     */
    @PostMapping("updateTeacher")
    @ApiOperation(value = "讲师修改功能")
    public R updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = teacherService.updateById(eduTeacher);
        if (flag){
            return R.ok();
        }else {
            return R.error();
        }
    }
}

