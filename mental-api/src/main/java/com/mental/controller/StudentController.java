package com.mental.controller;

import com.mental.common.Result;
import com.mental.pojo.PageQuery;
import com.mental.common.ResultCode;
import com.mental.pojo.Student;
import com.mental.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 添加学生
     * @param student
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Student student){
        try {
            log.info("添加学生：" + student);
            studentService.add(student);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    @GetMapping("/pageQuery")
    public Result pageQuery(PageQuery pageQuery){
        try {
            log.info("分页查询：{}", pageQuery);
            Map<String, Object> map = studentService.pageQuery(pageQuery);
            return new Result(ResultCode.SUCCESS, map);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 根据学号删除学生信息
     * @return
     */
    @DeleteMapping("/delete")
    public Result deleteBySid(Long[] sids){
        try {
            log.info("根据sid删除学生信息：{}", Arrays.toString(sids));
            for (Long sid : sids) {
                studentService.deleteBySid(sid);
            }
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 根据学号查询学生信息
     * @param sid
     * @return
     */
    @GetMapping("{sid}")
    public Result selectBySid(@PathVariable Long sid){
        try {
            log.info("查询学生信息：{}", sid);
            Student student = studentService.selectBySid(sid);
            return new Result(ResultCode.SUCCESS, student);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 修改学生信息
     * @param student
     * @return
     */
    @PostMapping("/update")
    public Result updateById(@RequestBody Student student){
        try {
            log.info("修改学生信息：{}", student);
            studentService.update(student);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }
}
