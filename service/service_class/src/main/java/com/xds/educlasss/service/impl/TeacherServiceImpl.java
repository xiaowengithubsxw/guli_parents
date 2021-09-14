package com.xds.educlasss.service.impl;

import com.xds.educlasss.entity.Teacher;
import com.xds.educlasss.mapper.TeacherMapper;
import com.xds.educlasss.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-09-14
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
//    @Override
//    public List<Teacher> getAll() {
//        //查询所有讲师
//        QueryWrapper<Teacher> Twrapper=new QueryWrapper();
//          Twrapper.ne("id", "0");
//        List<Teacher> teachers = baseMapper.selectList(Twrapper);
//        //查询所有课程
//        QueryWrapper<Classs> Cwrapper = new QueryWrapper<>();
//        Cwrapper.ne("id", "0");
//
//    }

  /*  @Override
    public Fin fin(String id) {
        //调用mapper
        Fin fin = baseMapper.getAll(id);
        return fin;
    }*/
}
