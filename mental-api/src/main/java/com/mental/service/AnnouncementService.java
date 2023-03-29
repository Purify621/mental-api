package com.mental.service;

import com.mental.common.Result;
import com.mental.pojo.Announcement;
import com.mental.pojo.PageQuery;

import java.util.Map;

public interface AnnouncementService {

    /**
     * 查询所有
     * @return
     */
    Result selectAll();

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    Map<String,Object> selectPageQuery(PageQuery pageQuery);

    /**
     * 根据id查询信息
     */
    Result selectById(Integer id);

    /**
     * 添加
     * @param announcement
     * @return
     */
    Result add(Announcement announcement);

    /**
     * 根据id删除
     */
    Result deleteById(Integer id);

}
