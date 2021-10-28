package com.xds.educenter.service;

import com.xds.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xds.educenter.entity.vo.Register;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-09-16
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(Register register);

    Integer countRegister(String day);
}
