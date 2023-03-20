package com.xt.jiguo.service.impl;

import com.github.pagehelper.PageHelper;
import com.xt.jiguo.dao.CoolItemDao;
import com.xt.jiguo.entity.CoolItem;
import com.xt.jiguo.entity.vo.CoolItemVo;
import com.xt.jiguo.service.CoolItemService;

import com.xt.jiguo.utils.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class CoolItemServiceImpl implements CoolItemService {
    @Resource
    private CoolItemDao dao;
    @Resource
    private RedisTemplate template;

    @Override
    public List<CoolItemVo> findByPage(Integer pageNo, Integer pageSize, String order) {

        String key="coolitem-"+pageNo+"-"+pageSize+"-"+order;
        List<CoolItemVo> vos=null;
        /*if (template.exists(key)){
            vos=(List<CoolItemVo>) template.get(key.getBytes());
        }else {
            PageHelper.startPage(pageNo,pageSize);
            vos=dao.selectByOrder(order);
            template.set(key,vos);
        }*/
        PageHelper.startPage(pageNo,pageSize);
        vos=dao.selectByOrder(order);
        return vos;
    }

    @Override
    public List<CoolItem> findByPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return dao.selectItems();
    }

    @Override
    public int add(CoolItem item) {
        int rt=dao.insert(item);
        if (rt>0){
            Set<String> keys=template.keys("coolitem-*");
            String[] arrs=new String[keys.size()];
            keys.toArray(arrs);
            template.del(arrs);
        }
        return rt;
    }

    @Override
    public int modify(CoolItem item) {
        int rt=dao.updateByPrimaryKeySelective(item);
        if (rt>0){
            Set<String> keys=template.keys("coolitem-*");
            String[] arrs=new String[keys.size()];
            keys.toArray(arrs);
            template.del(arrs);
        }
        return rt;
    }
}
