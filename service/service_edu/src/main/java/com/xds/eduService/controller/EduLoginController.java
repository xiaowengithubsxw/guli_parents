package com.xds.eduService.controller;

import com.xds.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Bai Xu
 * @Date 2021/8/26 9:28
 * @Version 1.0
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin//解决跨域
public class EduLoginController {
    @PostMapping("login")
    public R login(){
        return R.ok().data("token", "admin");
    }
    @GetMapping("info")
    public R info(){
      return   R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
