package com.mental.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mental.common.Constant;
import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.dao.StudentDao;
import com.mental.pojo.PageQuery;
import com.mental.pojo.Student;
import com.mental.pojo.UserDetail;
import com.mental.service.StudentService;
import com.mental.utils.JwtUtil;
import com.mental.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 学生
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    /**
     * 登录
     *
     * @param student
     * @return
     */
    @Override
    public Result login(Student student) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("3." + student.getUsername(), student.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //生成jwt
        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();
        String key = userDetail.getStatus() + ":" + userDetail.getUsername() + ":" + userDetail.getId();
        String jwt = JwtUtil.createJWT(key);

        Student stu = studentDao.selectById(userDetail.getId());
        LinkedHashMap user = JSONObject.parseObject(JSONObject.toJSONString(stu), LinkedHashMap.class);
        user.remove("password");

        //将信息存入redis
        redisCache.setCacheObject(key, userDetail, Constant.TIMEOUT, TimeUnit.MILLISECONDS);

        //创建返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("token", jwt);
        resultMap.put("user", user);

        return new Result(ResultCode.SUCCESS, resultMap);
    }

    /**
     * 添加学生
     *
     * @param student
     */
    @Override
    public void add(Student student) {
        studentDao.insert(student);
    }

    /**
     * 分页查询
     *
     * @return
     */
    @Override
    public Map<String, Object> pageQuery(PageQuery pageQuery) {
        Page<Student> page = new Page<Student>(pageQuery.getCurrentPage(), pageQuery.getPageSize());
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>();
        queryWrapper.like(pageQuery.getSid() != null, Student::getSid, pageQuery.getSid())
                .eq(pageQuery.getGender() != null && pageQuery.getGender().length()>0, Student::getGender, pageQuery.getGender())
                .eq(pageQuery.getAge() != null, Student::getAge, pageQuery.getAge());

        studentDao.selectPage(page, queryWrapper);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("total", page.getTotal());
        result.put("currentPage", pageQuery.getCurrentPage());
        result.put("pageSize", pageQuery.getPageSize());
        result.put("data", page.getRecords());

        return result;
    }

    /**
     * 根据学号删除学生信息
     *
     * @param sid
     */
    @Override
    public void deleteBySid(Long sid) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>();
        queryWrapper.eq(sid != null, Student::getSid, sid);

        studentDao.delete(queryWrapper);
    }

    /**
     * 根据学号查询学生信息
     *
     * @param sid
     * @return
     */
    @Override
    public Student selectBySid(Long sid) {
        LambdaQueryWrapper<Student> queryWrapper = new LambdaQueryWrapper<Student>();
        queryWrapper.eq(sid != null, Student::getSid, sid);

        Student student = studentDao.selectOne(queryWrapper);

        return student;
    }

    /**
     * 修改学生信息
     *
     * @param student
     */
    @Override
    public void update(Student student) {
        studentDao.updateById(student);
    }
}
