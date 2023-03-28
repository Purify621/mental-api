package com.mental.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 管理员类
 */
@Data
public class Admin implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    //id
    private Long id;
    //账号
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //角色
    private String role;
    //头像
    private String avatar;
    //学校
    private String school;
    // 性别
    private String gender;
}
