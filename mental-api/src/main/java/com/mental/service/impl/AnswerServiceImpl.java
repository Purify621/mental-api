package com.mental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mental.dao.AnswerDao;
import com.mental.pojo.Answer;
import com.mental.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 答案
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerDao, Answer> implements AnswerService {
    @Autowired
    private AnswerDao answerDao;

    /**
     * 根据问题id获取信息
     *
     * @param sid
     */
    @Override
    public List<Answer> selectAllByQuestionId(Long sid) {
        LambdaQueryWrapper<Answer> lqw = new LambdaQueryWrapper<Answer>();
        lqw.eq(sid != null, Answer::getQuestionId, sid);
        return answerDao.selectList(lqw);
    }
}
