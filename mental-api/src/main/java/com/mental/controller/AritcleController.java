package com.mental.controller;

import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.pojo.Article;
import com.mental.pojo.PageQuery;
import com.mental.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 科普文章
 */

@RestController
@RequestMapping("/article")
@Slf4j
public class AritcleController {
    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Article article){
        return articleService.add(article);
    }

    /**
     * 根据id获取信息
     */
    @GetMapping("{id}")
    public Result selectById(@PathVariable Integer id){
        return articleService.selectById(id);
    }

    /**
     * 获取所有信息
     */
    @GetMapping("/all")
    public Result selectAll(){
        return articleService.selectAll();
    }

    /**
     * 更新信息
     * @param article
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Article article){ return articleService.update(article); }

    /**
     * 分页查询
     * @param pageQuery 分页查询对象
     * @return
     */
    @GetMapping("/pageQuery")
    public Result selectPageQuery(PageQuery pageQuery){
        try {
            Map<String,Object> map = articleService.selectPageQuery(pageQuery);
            return new Result(ResultCode.SUCCESS,map);
        }catch (Exception e){
            e.printStackTrace();
            // 捕获异常
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     *  获取所有试题信息包含已禁用
     * @return
     */
    @GetMapping("/alls")
    public Result selectAllS(){return articleService.selectAllS();}
    /**
     * 根据id删除对应文章
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable Integer id){
        return articleService.deleteById(id);
    }

    /**
     * 修改文章启用状态
     */
    @PostMapping("/status/{id}")
    public Result updateStatusById(@PathVariable Integer id){
        return articleService.updateStatusById(id);
    }
}
