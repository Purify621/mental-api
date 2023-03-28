package com.mental.service;

import com.mental.common.Result;
import com.mental.pojo.PageQuery;
import com.mental.pojo.Teacher;

import java.util.Map;

/**
 * 教师
 */
public interface TeacherService {
    /**
     * 登录
     * @param teacher
     * @return
     */
    Result login(Teacher teacher);

    /**
     * 添加教师
     * @param teacher
     */
    void add(Teacher teacher);

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    Map<String, Object> pageQuery(PageQuery pageQuery);

    /**
     * 根据工号删除教师信息
     * @param tid
     */
    void deleteByTid(Long tid);

    /**
     * 根据工号查询教师信息
     * @param tid
     * @return
     */
    Teacher selectByTid(Long tid);

    /**
     * 修改教师信息
     * @param teacher
     */
    void updateByTid(Teacher teacher);

}
