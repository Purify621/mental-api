package com.mental;

import com.mental.pojo.Question;
import com.mental.pojo.QuestionBank;
import com.mental.service.QuestionBankService;
import com.mental.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ServiceTest {
    @Autowired
    private QuestionBankService questionBankService;

    @Autowired
    QuestionService questionService;

    /**
     * 获取所有题库信息
     */
    @Test
    void getAllByQuestionBank(){
        List<QuestionBank> all = questionBankService.getAll(1);
        System.out.println(all);
    }

    /**
     * 根据题库id获取问题
     */
    @Test
    void selectAllByBankId(){
        List<Question> questions = questionService.selectAllByBankId(1L);
        System.out.println(questions);
    }
}
