package com.xds.eduService.service;

import com.xds.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xds.eduService.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-08-28
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 添加课程分类
     * @param file
     */
    void saveSubject(MultipartFile file,EduSubjectService subjectService);

    /**
     * 课程分类列表树形结构
     * @return
     */

    List<OneSubject> getAllOneTwoSubject();
}
