package com.mental.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserResultP {
    @Id
    private Integer id; //id
    private Integer userId; //用户id
    private Integer questionId; //试题id
    private String problemName; //试题名称
    private Integer score; //分数
    private String picturebox; //试题图片
    private String title; // 试题标题
}
