package com.xt.jiguo.service;

import com.xt.jiguo.entity.CoolItem;
import com.xt.jiguo.entity.vo.CoolItemVo;

import java.util.List;

public interface CoolItemService  {
    List<CoolItemVo> findByPage(Integer pageNo,Integer pageSize,String order);
    List<CoolItem> findByPage(Integer pageNo,Integer pageSize);
    int add(CoolItem item);
    int modify(CoolItem item);



}
