package com.xt.jiguo.service.impl;

import com.xt.jiguo.dao.CoolItemDao;
import com.xt.jiguo.dao.CoolItemThumbDao;
import com.xt.jiguo.entity.CoolItem;
import com.xt.jiguo.entity.CoolItemThumb;
import com.xt.jiguo.service.CoolItemThumbService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class CoolItemThumbImpl implements CoolItemThumbService {
    @Resource
    private CoolItemDao dao;
    @Resource
    private CoolItemThumbDao thumbDao;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int add(CoolItemThumb thumb) {
        CoolItem item=dao.selectByPrimaryKey(thumb.getItemId());
        if (item==null){
            throw new RuntimeException("目标商品不存在");
        }
        int cnt=thumbDao.selectCountByThumb(thumb);
        if (cnt==1){
            throw new RuntimeException("已经给改商品点过赞");
        }
        return thumbDao.insert(thumb);
    }
}
