package com.mental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mental.dao.QuestionDao;
import com.mental.pojo.Answer;
import com.mental.pojo.Question;
import com.mental.service.AnswerService;
import com.mental.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionDao, Question> implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerService answerService;

    /**
     * 根据题库id获取所有问题
     *
     * @return
     */
    @Override
    public List<Question> selectAllByBankId(Long bankId) {
        //根据题库id查询问题
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<Question>();
        lqw.eq(bankId != null, Question::getBankId, bankId);
        List<Question> questions = questionDao.selectList(lqw);

        //设置问题所对应的答案
        for (Question question : questions) {
            Long sid = question.getId();
            List<Answer> answers = answerService.selectAllByQuestionId(sid);
            question.setAnwsers(answers);
        }

        return questions;
    }
}
