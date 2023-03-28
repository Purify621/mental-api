package com.mental.exception;

import com.alibaba.fastjson.JSON;
import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.utils.WebUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录异常处理
 */
@Component
public class LoginException implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result result = new Result(ResultCode.SUCCESS, "用户名或密码错误");
        String resultStr = JSON.toJSONString(result);

        WebUtils.renderString(response, resultStr);
    }
}
