package com.xds.educenter.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Bai Xu
 * @Date 2021/9/16 10:44
 * @Version 1.0
 */
@Data
public class Register {
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "昵称")
    private String nickname;
    @ApiModelProperty(value = "用户名")
    private String username;
}
