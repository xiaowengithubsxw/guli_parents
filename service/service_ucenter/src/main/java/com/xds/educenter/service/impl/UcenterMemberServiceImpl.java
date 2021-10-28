package com.xds.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xds.commonutils.JwtUtils;
import com.xds.commonutils.MD5;
import com.xds.educenter.entity.UcenterMember;
import com.xds.educenter.entity.vo.Register;
import com.xds.educenter.mapper.UcenterMemberMapper;
import com.xds.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xds.service.exception.GuliException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-16
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    //登录
    @Override
    public String login(UcenterMember member) {
        String username = member.getUsername();
        String password = member.getPassword();
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            throw new GuliException(20001, "登录失败");
        }
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        UcenterMember userName = baseMapper.selectOne(wrapper);
        if (userName==null){
            throw new GuliException(20001, "用户名不存在");
        }
        if (!MD5.encrypt(password).equals(userName.getPassword())){
            throw new GuliException(20001, "密码错误");
        }
        if (userName.getIsDisabled()){
            throw new GuliException(20001, "用户被禁用");
        }
        String token = JwtUtils.getJwtToken(userName.getId(), userName.getNickname());
        return token;

    }
    //注册
    @Override
    public void register(Register register) {
        String username = register.getUsername();
        String password = register.getPassword();
        String nickname = register.getNickname();
        //判断是否非空
        if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)||StringUtils.isEmpty(nickname)){
            throw new GuliException(20001, "注册失败");
        }
        //判断用户名是否重复
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Integer count = baseMapper.selectCount(wrapper);
        if (count>0){
            throw  new GuliException(20001, "用户名也存在");
        }
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setNickname(nickname);
        ucenterMember.setUsername(username);
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);//用户不禁用
        ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        //数据添加到数据库中
        baseMapper.insert(ucenterMember);

    }
    //查询某天注册人数
    @Override
    public Integer countRegister(String day) {
     return    baseMapper.countRegisterDay(day);

    }
}
