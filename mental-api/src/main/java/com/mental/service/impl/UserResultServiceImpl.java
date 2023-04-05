package com.mental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.dao.UserResultDao;
import com.mental.pojo.PageQuery;
import com.mental.pojo.UserResult;
import com.mental.pojo.UserResultP;
import com.mental.service.UserResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserResultServiceImpl implements UserResultService {
    @Autowired
    private UserResultDao userResultDao;

    /**
     * 保存用户答题结果
     * @param userResult
     * @return
     */
    @Override
    public Result save(UserResult userResult) {

        userResultDao.insert(userResult);

        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据用户id进行分页查询
     * @param pageQuery
     * @return
     */
    @Override
    public Result selectByUserId(PageQuery pageQuery) {
        //分页
        Page<UserResult> page = new Page(pageQuery.getCurrentPage(), pageQuery.getPageSize());

        //条件
        LambdaQueryWrapper<UserResult> queryWrapper = new LambdaQueryWrapper<UserResult>();
        queryWrapper.like(pageQuery.getSid()!=null,UserResult::getUserId,pageQuery.getSid());

        userResultDao.selectPage(page, queryWrapper);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", page.getTotal());
        resultMap.put("data", page.getRecords());

        return new Result(ResultCode.SUCCESS, resultMap);
    }

    /**
     * 管理员分页查询 获取所有用户答题信息
     * @return
     */
    @Override
    public Result selectAll(PageQuery pageQuery) {
        Page<UserResult> page = new Page<>(pageQuery.getCurrentPage(),pageQuery.getPageSize());
        LambdaQueryWrapper<UserResult> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(pageQuery.getPname()!=null,UserResultP::getPname,pageQuery.getPname());
        userResultDao.selectPage(page,lambdaQueryWrapper);
        Map<String,Object> result = new HashMap<>();
        result.put("total",page.getTotal());
        result.put("data",page.getRecords());

        return new Result(ResultCode.SUCCESS,result);
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    public Result deleteById(Integer id) {
        userResultDao.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 根据id和uid查询答题数据
     * @param id 试题id
     * @param uid 用户id
     * @return
     */
    @Override
    public Result selectQid(Integer id,Integer uid) {
        QueryWrapper<UserResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserResultP::getQuestionId,id).eq(UserResultP::getUserId,uid);
        List<UserResult> userResults = userResultDao.selectList(queryWrapper);
        return new Result(ResultCode.SUCCESS,userResults);
    }
}
