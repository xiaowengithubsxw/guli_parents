package com.xds.educenter.controller;


import com.xds.commonutils.JwtUtils;
import com.xds.commonutils.R;
import com.xds.educenter.entity.UcenterMember;
import com.xds.educenter.entity.vo.Register;
import com.xds.educenter.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-09-16
 */
@RestController
@RequestMapping("/educenter/member")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;
    //登录
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member){
     String token=  ucenterMemberService.login(member);
        System.out.println("获取到了用token"+token);
     return R.ok().data("token",token);
    }
    //注册
    @PostMapping("register")
    public R registerUser(@RequestBody Register register){
        ucenterMemberService.register(register);
        return R.ok();
    }
    //根据token信息获取用户信息
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request){
            //调用jwt工具方法，获取request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库，获取用户信息
        UcenterMember member = ucenterMemberService.getById(memberId);
        System.out.println("获取到了用户信息"+member.getUsername()+"用户id"+memberId);
        return R.ok().data("userInfo",member);

    }
    //查询某一天的注册人数
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable("day")String day){
      Integer count=  ucenterMemberService.countRegister(day);
        return R.ok().data("countRegister",count);
    }
}

