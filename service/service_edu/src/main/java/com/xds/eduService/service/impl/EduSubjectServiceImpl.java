package com.xds.eduService.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xds.eduService.entity.EduSubject;
import com.xds.eduService.entity.excel.SubjectData;
import com.xds.eduService.entity.subject.OneSubject;
import com.xds.eduService.entity.subject.TwoSubject;
import com.xds.eduService.listener.SubjectExcelListener;
import com.xds.eduService.mapper.EduSubjectMapper;
import com.xds.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-28
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    /**
     * 添加课程分类
     * @param file
     */
    @Override
    public void saveSubject(MultipartFile file,EduSubjectService subjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 课程分类列表树形
     * @return
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //二级分类
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);
        //创建list集合，用于存储最终封装数据
        List<OneSubject> finalSubjectList=new ArrayList<>();
        //封装一级分类
        for (int i = 0; i < oneSubjectList.size(); i++) {
            //获取每个一级分类
            EduSubject eduSubject = oneSubjectList.get(i);
            OneSubject oneSubject=new OneSubject();
            BeanUtils.copyProperties(eduSubject,oneSubject);
            finalSubjectList.add(oneSubject);
            //封装二级分类
            List<TwoSubject> twoFinalSubjectList=new ArrayList<>();
            //遍历二级分类list集合
            for (int j = 0; j < twoSubjectList.size(); j++) {
                //获取每个二级分类
                EduSubject twoSubject = twoSubjectList.get(j);
                //判断二级分类paren_id和一级分类的id是否一样
                if (twoSubject.getParentId().equals(eduSubject.getId())){
                    TwoSubject twoSubject1=new TwoSubject();
                    BeanUtils.copyProperties(twoSubject, twoSubject1);
                    twoFinalSubjectList.add(twoSubject1);
                }
            }
            //把一级分类下所有的二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);
        }

        return finalSubjectList;
    }
}
