package com.xds.educlasss.service;

import com.xds.educlasss.entity.VO.jiaoshi;
import com.xds.educlasss.entity.Zonghe;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author testjava
 * @since 2021-09-14
 */
public interface ZongheService extends IService<Zonghe> {

    List<jiaoshi> getAll();
}
