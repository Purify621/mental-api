package com.mental.controller;

import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.pojo.Announcement;
import com.mental.pojo.PageQuery;
import com.mental.service.AnnouncementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/announcement")
@Slf4j
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/all")
    public Result selectAll(){ return announcementService.selectAll(); }

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    @GetMapping("/pageQuery")
    public Result selectPageQuery(PageQuery pageQuery){
        try {
            Map<String,Object> map = announcementService.selectPageQuery(pageQuery);
            return new Result(ResultCode.SUCCESS,map);
        }catch (Exception e){
            return new Result(ResultCode.ERROR,e);
        }
    }

    /**
     * 添加公告
     * @param announcement
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Announcement announcement){ return announcementService.add(announcement);}

    /**
     * 根据id查找当前信息
     */
    @GetMapping("{id}")
    public Result selectById(@RequestBody Integer id){ return announcementService.selectById(id); }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    public Result deleteById(@PathVariable Integer id){ return announcementService.deleteById(id); }

}
