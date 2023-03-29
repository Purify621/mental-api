package com.mental.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mental.common.Result;
import com.mental.common.ResultCode;
import com.mental.dao.AnnouncementDao;
import com.mental.pojo.Announcement;
import com.mental.pojo.PageQuery;
import com.mental.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//  自动把当前接口注册到spring容器中
@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    /**
     * 查询所有
     * @return
     */
    @Override
    public Result selectAll() {
        List<Announcement> announcements = announcementDao.selectAll();
        return new Result(ResultCode.SUCCESS,announcements);
    }

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    @Override
    public Map<String, Object> selectPageQuery(PageQuery pageQuery) {
        Page<Announcement> page = new Page<Announcement>(pageQuery.getCurrentPage(),pageQuery.getPageSize());
        LambdaQueryWrapper<Announcement> queryWrapper = new LambdaQueryWrapper<>();

        announcementDao.selectPage(page,queryWrapper);

        Map<String,Object> result = new HashMap<>();
        result.put("total",page.getTotal());
        result.put("currentPage",pageQuery.getCurrentPage());
        result.put("pageSize",pageQuery.getPageSize());
        result.put("data",page.getRecords());

        return result;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Result selectById(Integer id) {
        Announcement announcement = announcementDao.selectById(id);
        announcement.setTotal(announcement.getTotal()+1);
        QueryWrapper<Announcement> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Announcement::getId,id);
        announcementDao.update(announcement,queryWrapper);
        return new Result(ResultCode.SUCCESS, announcementDao);
    }

    /**
     * 添加
     * @param announcement
     * @return
     */
    @Override
    public Result add(Announcement announcement) {
        announcementDao.insert(announcement);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Result deleteById(Integer id) {
        announcementDao.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
