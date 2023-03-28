package com.mental.pojo;

import lombok.Data;

/**
 * 分页查询条件对象
 */
@Data
public class PageQuery {
    //当前页
    private Integer currentPage;
    //每页条数
    private Integer pageSize;

    //学号
    private Long sid;
    //性别
    private String gender;
    //年龄
    private Integer age;

    //工号
    private Long tid;
    //职称
    private String title;
    //学校
    private String school;

    // 文章类型
    private String type;
}
