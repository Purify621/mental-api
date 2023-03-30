package com.mental.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * 用户答题结果
 */
@Data
@TableName("tb_result")
public class UserResult extends UserResultP{

    private String questionContent; //试题内容



}
