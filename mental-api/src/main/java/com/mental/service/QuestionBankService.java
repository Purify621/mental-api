package com.mental.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mental.pojo.QuestionBank;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 题库
 */
@Service
public interface QuestionBankService extends IService<QuestionBank> {
    /**
     * 获取所有题库
     * @return
     * @param status
     */
    List<QuestionBank> getAll(Integer status);

    /**
     * 修改题库状态
     */
    void updateStatusById(Long id);

    /**
     * 删除题库
     * @param id
     */
    void deleteById(Long id);
}
