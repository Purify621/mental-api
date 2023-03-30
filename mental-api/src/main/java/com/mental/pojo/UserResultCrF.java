package com.mental.pojo;

import lombok.Data;

import java.util.List;

@Data
public class UserResultCrF extends UserResultP{
    List<QuestionContent> questionContent;
}
