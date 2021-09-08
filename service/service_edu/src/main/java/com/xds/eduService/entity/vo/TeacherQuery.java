package com.xds.eduService.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Bai Xu
 * @Date 2021/8/24 8:26
 * @Version 1.0
 */
@Data
public class TeacherQuery {
    @ApiModelProperty(value = "讲师名称，模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师模 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "开始时间")
    private String begin;
    @ApiModelProperty(value = "结束时间")
    private String end;
}
