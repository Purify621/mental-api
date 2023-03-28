package com.mental.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mental.common.Constant;
import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.dao.TeacherDao;
import com.mental.pojo.PageQuery;
import com.mental.pojo.Teacher;
import com.mental.pojo.UserDetail;
import com.mental.service.TeacherService;
import com.mental.utils.JwtUtil;
import com.mental.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 教师
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录
     *
     * @param teacher
     * @return
     */
    @Override
    public Result login(Teacher teacher) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("2." + teacher.getUsername(), teacher.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //生成token
        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();
        String key = userDetail.getStatus() + ":" + userDetail.getUsername();
        String jwt = JwtUtil.createJWT(key);

        Teacher tea = teacherDao.selectById(userDetail.getId());
        LinkedHashMap user = JSONObject.parseObject(JSONObject.toJSONString(tea), LinkedHashMap.class);
        user.remove("password");

        //将用户信息存入redis
        redisCache.setCacheObject(key, userDetail, Constant.TIMEOUT, TimeUnit.MILLISECONDS);

        //创建返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("token", jwt);
        resultMap.put("user", user);

        return new Result(ResultCode.SUCCESS, resultMap);
    }

    /**
     * 添加教师
     *
     * @param teacher
     */
    @Override
    public void add(Teacher teacher) {
        teacherDao.insert(teacher);
    }

    /**
     * 分页查询
     *
     * @param pageQuery
     * @return
     */
    @Override
    public Map<String, Object> pageQuery(PageQuery pageQuery) {
        Page<Teacher> page = new Page<Teacher>(pageQuery.getCurrentPage(), pageQuery.getPageSize());

        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<Teacher>();
        queryWrapper.like(pageQuery.getTid() != null, Teacher::getTid, pageQuery.getTid())
                .eq(!StringUtils.isEmpty(pageQuery.getGender()), Teacher::getGender, pageQuery.getGender())
                .like(pageQuery.getTitle() != null, Teacher::getTitle, pageQuery.getTitle())
                .like(pageQuery.getSchool() != null, Teacher::getSchool, pageQuery.getSchool());


        teacherDao.selectPage(page, queryWrapper);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", page.getTotal());
        map.put("currentPage",pageQuery.getCurrentPage());
        map.put("pageSize", pageQuery.getPageSize());
        map.put("data",page.getRecords());

        return map;
    }

    /**
     * 根据工号删除教师信息
     *
     * @param tid
     */
    @Override
    public void deleteByTid(Long tid) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<Teacher>();
        queryWrapper.eq(tid != null, Teacher::getTid, tid);

        teacherDao.delete(queryWrapper);
    }

    /**
     * 根据工号查询教师信息
     *
     * @param tid
     * @return
     */
    @Override
    public Teacher selectByTid(Long tid) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<Teacher>();
        queryWrapper.eq(tid != null, Teacher::getTid, tid);

        Teacher teacher = teacherDao.selectOne(queryWrapper);

        return teacher;
    }

    /**
     * 修改教师信息
     *
     * @param teacher
     */
    @Override
    public void updateByTid(Teacher teacher) {
        LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<Teacher>();
        queryWrapper.eq(teacher.getTid() != null, Teacher::getTid, teacher.getTid());

        teacherDao.update(teacher, queryWrapper);
    }
}
