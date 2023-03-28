package com.mental.controller;

import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.pojo.PageQuery;
import com.mental.pojo.Teacher;
import com.mental.service.TeacherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 教师
 */
@RestController
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher){
        try {
            log.info("添加教师：{}", teacher);
            teacherService.add(teacher);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
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
        log.info("教师，分页查询：{}", pageQuery);
        Map<String, Object> map = teacherService.pageQuery(pageQuery);
        return new Result(ResultCode.SUCCESS, map);
    }

    /**
     * 删除教师
     * @param tids
     * @return
     */
    @DeleteMapping("delete")
    public Result deleteByTid(Long[] tids){
        log.info("删除教师：{}", Arrays.toString(tids));
        for (Long tid : tids) {
            teacherService.deleteByTid(tid);
        }
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据工号查询教师信息
     * @param tid
     * @return
     */
    @GetMapping("{tid}")
    public Result selectByTid(@PathVariable Long tid){
        try {
            log.info("查询教师信息：{}", tid);
            Teacher teacher = teacherService.selectByTid(tid);
            return new Result(ResultCode.SUCCESS, teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Teacher teacher){
        try {
            log.info("修改教师信息：{}", teacher);
            teacherService.updateByTid(teacher);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

}
