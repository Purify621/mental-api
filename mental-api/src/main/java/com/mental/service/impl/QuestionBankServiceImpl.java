package com.mental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.dao.AnswerDao;
import com.mental.dao.QuestionBankDao;
import com.mental.dao.QuestionDao;
import com.mental.pojo.Answer;
import com.mental.pojo.PageQuery;
import com.mental.pojo.Question;
import com.mental.pojo.QuestionBank;
import com.mental.service.QuestionBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 题库
 */
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankDao, QuestionBank> implements QuestionBankService {
    @Autowired
    private QuestionBankDao questionBankDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

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

    /**
     * 根据上传文件进行题库添加
     *
     * @param map
     * @return
     */
    @Override
    @Transactional
    public Result upload(Map map) {
        try {
            //获取题库数据
            //标题
            String title = map.get("title").toString();
            //详情
            String details = map.get("details").toString();
            String picture = map.get("picture").toString();
            String picturebox = map.get("picturebox").toString();
            String pictureinfo = map.get("pictureinfo").toString();

            //封装题库对象
            QuestionBank questionBank = new QuestionBank(title, details, picture, picturebox, pictureinfo);
            //向数据库添加数据
            questionBankDao.insert(questionBank);
            Long bankId = questionBank.getId();

            //获取问题数据
            Question question = null;
            Answer answer = null;
            List<Map> questions = (List) map.get("contents");
            for (Map questionMap : questions) {
                //获取问题
                String questionTitle = questionMap.get("title").toString();
                //创建问题对象，并保存到数据库
                question = new Question(bankId, questionTitle);
                questionDao.insert(question);
                Long questionId = question.getId();

                //获取对应答案
                Map answersMap = (Map) questionMap.get("anwsers");
                Set<String> keySet = answersMap.keySet();
                for (String key : keySet) {
                    Map answerMap = (Map) answersMap.get(key);
                    String str = answerMap.get("anwser").toString();
                    Integer score = Integer.parseInt(answerMap.get("anwser_score").toString());
                    answer = new Answer(questionId, str, score);
                    answerDao.insert(answer);
                }
            }
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }
}
