package com.mental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mental.dao.QuestionBankDao;
import com.mental.pojo.PageQuery;
import com.mental.pojo.QuestionBank;
import com.mental.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题库
 */
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankDao, QuestionBank> implements QuestionBankService {
    @Autowired
    private QuestionBankDao questionBankDao;

    /**
     * 获取所有题库
     *
     * @return
     * @param status
     */
    @Override
    public List<QuestionBank> getAll(Integer status) {
        LambdaQueryWrapper<QuestionBank> queryWrapper = new LambdaQueryWrapper<QuestionBank>();
        queryWrapper.eq(status == 0, QuestionBank::getStatus, status);

        return questionBankDao.selectList(queryWrapper);
    }

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    @Override
    public Map<String, Object> getAllPageQuery(PageQuery pageQuery) {
        Page<QuestionBank> page = new Page<QuestionBank>(pageQuery.getCurrentPage(),pageQuery.getPageSize());
        LambdaQueryWrapper<QuestionBank> queryWrapper = new LambdaQueryWrapper<QuestionBank>();
        questionBankDao.selectPage(page,queryWrapper);

        Map<String,Object> result = new HashMap<String, Object>();
        result.put("total", page.getTotal());
        result.put("currentPage", pageQuery.getCurrentPage());
        result.put("pageSize", pageQuery.getPageSize());
        result.put("data",page.getRecords());
        return result;
    }

    /**
     * 修改题库状态
     *
     * @param id
     */
    @Override
    public void updateStatusById(Long id) {
        QuestionBank questionBank = questionBankDao.selectById(id);
        //修改题库状态
        questionBank.setStatus(questionBank.getStatus() == 0 ? 1 : 0);
        questionBankDao.updateById(questionBank);
    }

    @Override
    public void deleteById(Long id) {
        questionBankDao.deleteById(id);
    }
}
