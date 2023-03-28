package com.mental.controller;

import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.pojo.Question;
import com.mental.service.AnswerService;
import com.mental.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/question")
@Slf4j
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /**
     * 根据题库id获取问题
     */
    @GetMapping("all")
    public Result selectAllByBankId(Long bankId){
        log.info("根据题库id获取问题:{}", bankId);
        try {
            List<Question> questions = questionService.selectAllByBankId(bankId);
            return new Result(ResultCode.SUCCESS, questions);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

}
