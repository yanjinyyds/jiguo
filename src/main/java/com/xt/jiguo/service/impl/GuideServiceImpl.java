package com.xt.jiguo.service.impl;

import com.github.pagehelper.PageHelper;
import com.xt.jiguo.dao.GuideDao;
import com.xt.jiguo.entity.Guide;
import com.xt.jiguo.entity.vo.GuideVo;
import com.xt.jiguo.service.GuideService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GuideServiceImpl implements GuideService {
    @Resource
    private GuideDao dao;


    @Override
    public List<GuideVo> findByPage(Integer pageNo, Integer pageSize, String order) {
        PageHelper.startPage(pageNo,pageSize);
        return dao.selectByOrder(order);
    }

    @Override
    public List<Guide> findByPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return dao.selectItems();
    }

    @Override
    public int add(Guide guide) {
        return dao.insert(guide);
    }

    @Override
    public int modify(Guide guide) {
        return dao.updateByPrimaryKeySelective(guide);
    }


}
