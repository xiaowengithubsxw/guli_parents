package com.xds.eduService.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xds.eduService.entity.EduSubject;
import com.xds.eduService.entity.excel.SubjectData;
import com.xds.eduService.service.EduSubjectService;
import com.xds.service.exception.GuliException;

/**
 * @Author Bai Xu
 * @Date 2021/8/28 16:02
 * @Version 1.0
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    public EduSubjectService subjectService;

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public SubjectExcelListener() {
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
            if (subjectData==null){
                throw new GuliException(20001,"文件数据为空");
            }
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
            if (existOneSubject==null){
                existOneSubject=new EduSubject();
                existOneSubject.setParentId("0");
                existOneSubject.setTitle(subjectData.getOneSubjectName());
                subjectService.save(existOneSubject);
            }
            String pid=existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (existTwoSubject==null){
            existTwoSubject=new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }
    }

    /**
     * 一级分类不能重复添加
     * @param subjectService
     * @param name
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService subjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject one = subjectService.getOne(wrapper);
        return one;
    }
    /**
     * 二级级分类不能重复添加
     * @param subjectService
     * @param name
     * @return
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject two = subjectService.getOne(wrapper);
        return two;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
