package com.xds.eduService.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


/**
 * 一级分类
 * @Author Bai Xu
 * @Date 2021/8/29 9:56
 * @Version 1.0
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    //一个一级分类有多个二级分类
    private List<TwoSubject> children=new ArrayList<>();
}
