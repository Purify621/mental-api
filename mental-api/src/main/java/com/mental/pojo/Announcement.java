package com.mental.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 公告
 */
@Data
@TableName("tb_announcement")
public class Announcement {
    // 标注主键
    @TableId
    // id
    private Integer id;
    // 标题
    private String title;
    // 数据
    private String data;
    // 日期
    private String date;
    // 状态
    private Integer status;
    // 总计
    private Integer total;
}
