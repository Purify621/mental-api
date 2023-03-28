package com.mental.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 答案
 */
@Data
@TableName("tb_answer")
public class Answer {
    private Long id; //id
    private Long questionId; //问题id
    private String answer; //答案
    private Integer answerScore;  //答案得分

    public Answer() {
    }

    public Answer(Long questionId, String answer, Integer answerScore) {
        this.questionId = questionId;
        this.answer = answer;
        this.answerScore = answerScore;
    }
}
