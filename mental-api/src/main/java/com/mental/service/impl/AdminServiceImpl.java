package com.mental.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mental.common.Constant;
import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.dao.AdminDao;
import com.mental.pojo.Admin;
import com.mental.pojo.Student;
import com.mental.pojo.UserDetail;
import com.mental.service.AdminService;
import com.mental.utils.JwtUtil;
import com.mental.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 管理员
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Result login(Admin admin) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("1." + admin.getUsername(), admin.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);

        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //生成jwt
        UserDetail userDetail = (UserDetail) authenticate.getPrincipal();
        String key = userDetail.getStatus() + ":" + userDetail.getUsername();
        String jwt = JwtUtil.createJWT(key);

        //查询用户信息
        Admin adm = adminDao.selectById(userDetail.getId());
        LinkedHashMap user = JSONObject.parseObject(JSONObject.toJSONString(adm), LinkedHashMap.class);
        user.remove("password");

        //将用户信息存在redis
        redisCache.setCacheObject(key, userDetail, Constant.TIMEOUT, TimeUnit.MILLISECONDS);

        //创建返回结果
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("token", jwt);
        resultMap.put("user", user);

        return new Result(ResultCode.SUCCESS, resultMap);
    }

    /**
     * 根据id获取管理员信息
     *
     * @param id
     * @return
     */
    @Override
    public Admin selectById(Long id) {
        Admin admin = adminDao.selectById(id);
        return admin;
    }

    /**
     * 修改管理员信息
     *
     * @param admin
     */
    @Override
    public void update(Admin admin) {
        adminDao.updateById(admin);
    }
}
