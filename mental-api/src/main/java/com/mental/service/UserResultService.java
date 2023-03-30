package com.mental.service;

import com.mental.common.Result;
import com.mental.pojo.PageQuery;
import com.mental.pojo.UserResult;

public interface UserResultService {
    Result save(UserResult userResult);

    Result selectByUserId(PageQuery pageQuery);

    Result deleteById(Integer id);

    Result selectQid(Integer id,Integer uid);
}
