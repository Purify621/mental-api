package com.mental.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.pojo.PageQuery;
import com.mental.pojo.QuestionContent;
import com.mental.pojo.UserResult;
import com.mental.pojo.UserResultCrF;
import com.mental.service.UserResultService;
import com.mental.utils.JwtUtil;
import com.qiniu.util.Json;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户答题结果
 */
@RestController
@RequestMapping("/result")
@Slf4j
public class UserResultController {
    @Autowired
    private UserResultService userResultService;

    @Autowired
    private HttpServletRequest httpServletRequest;



    /**
     * 保存答题结果
     */
    @PostMapping("/save")
    public Result saveResult(@RequestBody UserResultCrF userResult){
        Integer userId = getUserId(httpServletRequest);
        userResult.setUserId(userId);
        UserResult result = JSON.parseObject(JSON.toJSONString(userResult), UserResult.class);

        return userId==null ? new Result(ResultCode.ERROR) : userResultService.save(result);
    }

    /**
     * 获取当前用户的所有答题结果
     */
    @GetMapping("/get")
    public Result selectByUserId(PageQuery pageQuery){
        Integer userId = getUserId(httpServletRequest);
        pageQuery.setSid(userId);

        return userResultService.selectByUserId(pageQuery);
    }

    /**
     * 根据id删除
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        return userResultService.deleteById(id);
    }

    /**
     * 根据token获取用户id
     */
    public Integer getUserId(HttpServletRequest request){
        //获取token信息
        String token = httpServletRequest.getHeader("token");
        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);

            //3:student:1 类型:username:id
            String subject = claims.getSubject();
            Integer id  = new Integer(subject.split(":")[2]);
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @PostMapping("/qid")
    public Result selectQid(@RequestBody UserResult result){
        return userResultService.selectQid(result.getQuestionId(),result.getUserId());
    }
}
