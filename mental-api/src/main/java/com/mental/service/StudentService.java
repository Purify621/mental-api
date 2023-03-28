package com.mental.service;

import com.mental.common.Result;
import com.mental.pojo.PageQuery;
import com.mental.pojo.Student;

import java.util.Map;

/**
 * 学生
 */
public interface StudentService {
    /**
     * 登录
     * @param student
     * @return
     */
    Result login(Student student);

    /**
     * 添加学生
     * @param student
     */
    void add(Student student);

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    Map<String,Object> pageQuery(PageQuery pageQuery);

    /**
     * 根据学号删除学生信息
     * @param sid
     */
    void deleteBySid(Long sid);

    /**
     * 根据学号查询学生信息
     * @param sid
     * @return
     */
    Student selectBySid(Long sid);

    /**
     * 修改学生信息
     * @param student
     */
    void update(Student student);
}
