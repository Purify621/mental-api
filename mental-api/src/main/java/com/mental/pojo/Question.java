package com.mental.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * 问题表
 */
@Data
@TableName("tb_question")
public class Question {
    private Long id; //id
    private Long bankId;  //题库id
    private String title;  //标题
    @TableField(exist = false)
    private List<Answer> anwsers; //答案

    public Question() {
    }

    public Question(Long bankId, String title) {
        this.bankId = bankId;
        this.title = title;
    }
}
