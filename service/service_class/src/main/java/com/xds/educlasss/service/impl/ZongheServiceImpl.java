package com.xds.educlasss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xds.educlasss.entity.VO.jiaoshi;
import com.xds.educlasss.entity.VO.kecheng;
import com.xds.educlasss.entity.Zonghe;
import com.xds.educlasss.mapper.ZongheMapper;
import com.xds.educlasss.service.ZongheService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-14
 */
@Service
public class ZongheServiceImpl extends ServiceImpl<ZongheMapper, Zonghe> implements ZongheService {

    @Override
    public List<jiaoshi> getAll() {
        //查询所有讲师
        QueryWrapper<Zonghe> tWrapper = new QueryWrapper<>();
        tWrapper.eq("parent_id", "0");
        List<Zonghe> teacher = baseMapper.selectList(tWrapper);
        //查询所有课程
        QueryWrapper<Zonghe> cWrapper = new QueryWrapper<>();
        cWrapper.ne("parent_id", "0");
        List<Zonghe> calss = baseMapper.selectList(cWrapper);

        //创建list集合用于封装最终得到集合
        List<jiaoshi> finals=new ArrayList<>();
        //封装所有讲师
        for (int i = 0; i < teacher.size(); i++) {
            Zonghe zonghe = teacher.get(i);
            jiaoshi jiaoshi = new jiaoshi();
            BeanUtils.copyProperties(zonghe,jiaoshi);
            finals.add(jiaoshi);
            //封装所有课程
            //创建课程集合
            List<kecheng> claList=new ArrayList<>();
            //遍历所有课程
            for (int j = 0; j < calss.size(); j++) {
                    //获取每个课程
                Zonghe cla = calss.get(j);
                if (cla.getParentId().equals(zonghe.getId())){
                    kecheng kecheng = new kecheng();
                    BeanUtils.copyProperties(cla, kecheng);
                    claList.add(kecheng);
                }
            }
            jiaoshi.setChildren(claList);
        }

        return finals;
    }
}
