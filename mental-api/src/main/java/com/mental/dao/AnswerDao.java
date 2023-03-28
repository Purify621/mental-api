package com.mental.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mental.pojo.Answer;
import org.apache.ibatis.annotations.Mapper;

/**
 * 答案
 */
@Mapper
public interface AnswerDao extends BaseMapper<Answer> {
}
