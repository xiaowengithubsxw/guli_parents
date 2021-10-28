package com.xds.educenter.mapper;

import com.xds.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-09-16
 */
@Mapper
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
    //查询某天注册的人数
    Integer countRegisterDay(String day);
}
