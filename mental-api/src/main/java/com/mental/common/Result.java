package com.mental.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有响应返回结果类
 * @param
 */

@Data
public class Result{

    private Integer code; //编码：200成功，400和其它数字为失败
    private Object data;

    public Result(Integer code){
        this.code = code;
    }

    public Result(Integer code,Object data){
        this.code = code;
        this.data = data;
    }
}
