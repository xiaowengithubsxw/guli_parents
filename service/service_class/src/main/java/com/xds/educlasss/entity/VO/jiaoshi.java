package com.xds.educlasss.entity.VO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Bai Xu
 * @Date 2021/9/14 8:52
 * @Version 1.0
 */
@Data
public class jiaoshi {
    private String id;
    private String name;
    private List<kecheng> children=new ArrayList<>();

}
