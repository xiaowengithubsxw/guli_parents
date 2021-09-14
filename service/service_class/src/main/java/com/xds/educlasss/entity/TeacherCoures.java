package com.xds.educlasss.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2021-09-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TeacherCoures对象", description="")
public class TeacherCoures implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "时间")
    @TableField("classesTime")
    private String classesTime;

    @ApiModelProperty(value = "星期一")
    private String monday;

    @ApiModelProperty(value = "星期二")
    private String tuesday;

    @ApiModelProperty(value = "星期三")
    private String wednesday;

    @ApiModelProperty(value = "星期四")
    private String thursday;

    @ApiModelProperty(value = "星期五")
    private String friday;

    @ApiModelProperty(value = "星期六")
    private String saturday;

    @ApiModelProperty(value = "星期七")
    private String sunday;

    @ApiModelProperty(value = "地址")
    private String adress;


}
