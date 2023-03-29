package com.mental.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mental.pojo.Announcement;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AnnouncementDao extends BaseMapper<Announcement> {
    /**
     * 查询状态为启用的公告
     * @return
     */
    @Select("select id, title, data , date , status, total from tb_announcement where status = 1")
    List<Announcement> selectAll();

    /**
     * 查询所有 包含已禁用的
     * @return
     */
    @Select("select id, title, type, data , date ,status from tb_announcement")
    List<Announcement> selectAllS();

    /**
     * 根据id查询文章状态
     */
    @Select("select id,status from tb_announcement where id = #{id}")
    Announcement selectStatusById(Integer id);
}
