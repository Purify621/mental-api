package com.mental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mental.dao.AdminDao;
import com.mental.dao.StudentDao;
import com.mental.dao.TeacherDao;
import com.mental.pojo.Admin;
import com.mental.pojo.Student;
import com.mental.pojo.Teacher;
import com.mental.pojo.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] split = username.split("\\.");
        int index = Integer.parseInt(split[0]);
        username = split[1];

        if (index == 1) {
            //根据用户名查询用户信息
            LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Admin::getUsername, username);
            Admin admin = adminDao.selectOne(queryWrapper);
            //用户不存在
            if (Objects.isNull(admin)) {
                throw new RuntimeException("用户名不存在");
            }

            return new UserDetail(admin.getId(), admin.getUsername(), admin.getPassword(), index);
        }

        if (index == 2) {
            LambdaQueryWrapper<Teacher> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Teacher::getUsername, username);
            Teacher teacher = teacherDao.selectOne(queryWrapper);

            //用户不存在
            if (Objects.isNull(teacher)) {
                throw new RuntimeException("用户名不存在");
            }

            return new UserDetail(teacher.getId(), teacher.getUsername(), teacher.getPassword(), index);
        }

        if (index == 3) {
            LambdaQueryWrapper<Student> queryWrapper3 = new LambdaQueryWrapper<>();
            queryWrapper3.eq(Student::getUsername, username);
            Student student = studentDao.selectOne(queryWrapper3);

            //用户不存在
            if (Objects.isNull(student)) {
                throw new RuntimeException("用户名不存在");
            }

            return new UserDetail(student.getId(), student.getUsername(), student.getPassword(), index);
        }

        throw new RuntimeException("用户名不存在");
    }
}
