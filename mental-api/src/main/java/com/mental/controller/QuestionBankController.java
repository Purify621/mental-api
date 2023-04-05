package com.mental.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.pojo.Answer;
import com.mental.pojo.PageQuery;
import com.mental.pojo.Question;
import com.mental.pojo.QuestionBank;
import com.mental.service.AnswerService;
import com.mental.service.QuestionBankService;
import com.mental.service.QuestionService;
import com.mental.utils.QiniuUtil;
import com.qiniu.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 题库
 */
@RestController
@RequestMapping("/questionBank")
@Slf4j
public class QuestionBankController {
    @Autowired
    private QuestionBankService questionBankService;

    @Value("${qiniu.template-name}")
    private String templateName;
    @Autowired
    private QiniuUtil qiniuUtil;


    /**
     * 获取所有题库
     */
    @GetMapping("/all")
    public Result getAll(@RequestParam Integer status) {
        try {
            List<QuestionBank> bankList = questionBankService.getAll(status);
            return new Result(ResultCode.SUCCESS, bankList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 修改题库状态
     */
    @PostMapping("/status")
    public Result updateStatusById(Long id) {
        log.info("修改题库状态：{}", id);
        try {
            questionBankService.updateStatusById(id);
            return new Result(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 获取题库信息
     *
     * @param id
     * @return
     */
    @GetMapping("/info")
    public Result selectById(Long id) {
        try {
            QuestionBank questionBank = questionBankService.getById(id);
            return new Result(ResultCode.SUCCESS, questionBank);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 上传并添加题库
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //获取数据
            Map map = objectMapper.readValue(file.getInputStream(), Map.class);

            return questionBankService.upload(map);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 下载题库模板
     */
    @GetMapping("/download")
    public Result download(HttpServletResponse httpServletResponse) {
        //获取url
        String url = qiniuUtil.getUrl(templateName);

        return new Result(ResultCode.SUCCESS, url);
    }

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    @GetMapping("/pageQuery")
    public Result getAllPageQuery(PageQuery pageQuery){
        try {
            log.info("分页查询：{}", pageQuery);
            Map<String, Object> map = questionBankService.getAllPageQuery(pageQuery);
            return new Result(ResultCode.SUCCESS, map);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 删除试题
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        questionBankService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
