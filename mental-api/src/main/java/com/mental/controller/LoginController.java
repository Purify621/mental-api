package com.mental.controller;

import com.mental.common.Result;
import com.mental.pojo.Admin;
import com.mental.common.ResultCode;
import com.mental.pojo.Student;
import com.mental.pojo.Teacher;
import com.mental.service.AdminService;
import com.mental.service.StudentService;
import com.mental.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;

    /**
     * 学生登陆
     * @return
     */
    @PostMapping("/student")
    public Result studentLogin(@RequestBody Student student){
        return studentService.login(student);
    }

    /**
     * 教师登陆
     * @return
     */
    @PostMapping("/teacher")
    public Result teacherLogin(@RequestBody Teacher teacher){
        return teacherService.login(teacher);
    }

    /**
     * 管理员登陆
     * @return
     */
    @PostMapping("/admin")
    public Result teacherLogin(@RequestBody Admin admin){
        return adminService.login(admin);
    }
}
