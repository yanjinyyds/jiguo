package com.xt.jiguo.service.impl;

import com.xt.jiguo.dao.GuideDao;
import com.xt.jiguo.dao.GuideThumbDao;
import com.xt.jiguo.entity.Guide;
import com.xt.jiguo.entity.GuideThumb;
import com.xt.jiguo.service.GuideThumbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class GuideThumbServiceImpl implements GuideThumbService {
    @Resource
    private GuideDao dao;
    @Resource
    private GuideThumbDao thumbDao;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(GuideThumb thumb) {
        Guide item=dao.selectByPrimaryKey(thumb.getGuideId());
        if (item==null){
            throw new RuntimeException("目标商品不存在");
        }
        int cnt=thumbDao.selectCountByThumb(thumb);
        if (cnt==1){
            throw  new RuntimeException("已经给该商品点过赞");
        }
        return thumbDao.insert(thumb); 
    }





}
