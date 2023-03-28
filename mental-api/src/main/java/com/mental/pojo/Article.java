package com.mental.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 科普文章
 */

@Data
@TableName("tb_article")
public class Article {
    @TableId
    //id
    private Integer id;
    //标题
    private String title;
    //内容
    private String data;
    //创建时间
    private String date;
    //状态：1启用，0未启用
    private Integer status;
    // 类型
    private String type;

}
