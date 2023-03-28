package com.mental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mental.pojo.Answer;

import java.util.List;

/**
 * 答案
 */
public interface AnswerService extends IService<Answer> {
    /**
     * 根据问题id获取信息
     */
    List<Answer> selectAllByQuestionId(Long sid);
}
