package com.mental.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mental.pojo.Article;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 科普文章
 */
public interface ArticleDao extends BaseMapper<Article> {
    /**
     * 查询所有
     */
    @Select("select id, title, type, date ,status from tb_article where status = 1")
    List<Article> selectAll();

    /**
     * 查询所有包含禁用的
     * @return
     */
    @Select("select id, title, type, date ,status from tb_article")
    List<Article> selectAllS();

    /**
     * 根据id查询文章状态
     */
    @Select("select id, status from tb_article where id = #{id}")
    Article selectStatusById(Integer id);
}
