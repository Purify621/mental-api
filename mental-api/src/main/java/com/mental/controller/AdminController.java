package com.mental.controller;

import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.pojo.Admin;
import com.mental.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 管理员
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * 根据id获取管理员信息
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public Result selectById(@PathVariable Long id){
        try {
            log.info("获取管理员信息：{}", id);
            Admin admin = adminService.selectById(id);
            return new Result(ResultCode.SUCCESS, admin);
        } catch (Exception e){
            e.printStackTrace();
            return new Result(ResultCode.ERROR);
        }
    }

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody Admin admin){
        log.info("修改管理员信息：{}", admin);
        adminService.update(admin);
        return new Result(ResultCode.SUCCESS);
    }
}
