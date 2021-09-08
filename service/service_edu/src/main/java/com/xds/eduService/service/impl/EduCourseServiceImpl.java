package com.xds.eduService.service.impl;

import com.xds.eduService.entity.EduCourse;
import com.xds.eduService.entity.EduCourseDescription;
import com.xds.eduService.entity.vo.CourseInfoVo;
import com.xds.eduService.entity.vo.CoursePublishVo;
import com.xds.eduService.mapper.EduCourseMapper;
import com.xds.eduService.service.EduChapterService;
import com.xds.eduService.service.EduCourseDescriptionService;
import com.xds.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xds.eduService.service.EduVideoService;
import com.xds.service.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-29
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    EduCourseDescriptionService courseDescriptionService;//课程描述
    @Autowired
    private EduVideoService eduVideoService;//小节
    @Autowired
    private EduChapterService eduChapterService;//章节
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1.向课程表添加课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);

        int insert = baseMapper.insert(eduCourse);
        if (insert==0){
            //添加失败
            throw new GuliException(20001, "添加课程失败");
        }
        //获取添加之后的课程id
        String cid = eduCourse.getId();
        //2.向课程表简介表添加课程简介
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        eduCourseDescription.setId(cid);
        courseDescriptionService.save(eduCourseDescription);
            return cid;
    }

    /**
     * 根据课程id进行数据回显
     * @param courseId
     * @return
     */
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1.查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        //2.查询课程描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        CourseInfoVo courseInfoVo=new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);
        courseInfoVo.setDescription(courseDescription.getDescription());
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1.修改课程表
        EduCourse eduCourse=new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        baseMapper.updateById(eduCourse);
        //修改描述表
        EduCourseDescription eduCourseDescription=new EduCourseDescription();
        BeanUtils.copyProperties(courseInfoVo, eduCourseDescription);
        courseDescriptionService.updateById(eduCourseDescription);
    }

    /**
     * 根据课程id查询课程确认信息
     * @param id
     * @return
     */
    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo publishCourseInfo = baseMapper.getPublishCourseInfo(id);
        return publishCourseInfo;
    }

    /**
     * 删除课程
     * @param courseId
     */
    @Override
    public void removeCourse(String courseId) {
        //1.根据课程id删除课程小节
        eduVideoService.removeVideoByCourseId(courseId);
        //2.根据课程id删除课程章节
        eduChapterService.removeChapterByCourseId(courseId);
        //3.根据课程id删除课程描述
        courseDescriptionService.removeById(courseId);
        //4.根据课程id删除本身
        int result = baseMapper.deleteById(courseId);
        if (result==0){
            throw new GuliException(20001, "删除失败");
        }
    }
}
