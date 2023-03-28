package com.mental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mental.pojo.Question;

import java.util.List;

/**
 * 问题
 */
public interface QuestionService extends IService<Question> {
    /**
     * 根据题库id获取所有问题
     * @return
     */
    List<Question> selectAllByBankId(Long bankId);
}
