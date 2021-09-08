package com.xds.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xds.eduService.entity.EduChapter;
import com.xds.eduService.entity.EduVideo;
import com.xds.eduService.entity.chapter.ChapterVo;
import com.xds.eduService.entity.chapter.VideoVo;
import com.xds.eduService.mapper.EduChapterMapper;
import com.xds.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xds.eduService.service.EduVideoService;
import com.xds.service.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-08-29
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService videoService;
    /**
     * 课程大纲列表，根据课程id进行查询
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapperChapter=new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);
        //2.根据课程id查询课程里面所有小节
        QueryWrapper<EduVideo> wrapperVideo=new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = videoService.list(wrapperVideo);
        //创建list集合 用于数据的最终封装
        List<ChapterVo> finalList=new ArrayList<>();
        //3.遍历查询章节list集合进行封装
        for (int i = 0; i < eduChapterList.size(); i++) {
            //每个章节
            EduChapter eduChapter = eduChapterList.get(i);
            //eduChapter对象的值复制到ChapterVo里面
            ChapterVo chapterVo=new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            //把chapterVo放到最终list集合
            finalList.add(chapterVo);
            //创建集合，用于封装章节里面的小节
            List<VideoVo> voList=new ArrayList<>();
            //4.遍历查询小节list集合，进行封装
            for (int m = 0; m < eduVideoList.size(); m++) {
                //得到每个小节
                EduVideo eduVideo = eduVideoList.get(m);
                //判断：小节里面chapter和章节里面id是否一样
                    if (eduVideo.getChapterId().equals(eduChapter.getId())){
                        //进行封装
                        VideoVo videoVo=new VideoVo();
                        BeanUtils.copyProperties(eduVideo, videoVo);
                        //放到小节集合里面
                        voList.add(videoVo);
                    }
            }
            //把封装之后的小节list集合放到章节对象
            chapterVo.setChildren(voList);
        }
        return finalList;
    }

    /**
     * 删除章节
     * @param chapterId
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid章节id查询小节，如果有数据，不进行删除
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = videoService.count(wrapper);
        if (count>0){
            //有数据 进行删除
            throw  new  GuliException(20001,"不能删除");
        }else {
            //没有数据 进行删除
            int result = baseMapper.deleteById(chapterId);
            return result>0;
        }

    }

    /**
     * 删除章节根据课程id
     * @param courseId
     */
    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        baseMapper.delete(wrapper);
    }
}
