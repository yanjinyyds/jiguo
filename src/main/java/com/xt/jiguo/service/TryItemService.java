package com.xt.jiguo.service;

import com.xt.jiguo.entity.TryItem;
import com.xt.jiguo.entity.vo.TryItemVo;

import java.util.List;

public interface TryItemService {




    List<TryItemVo> getByPage(Integer pageNo,Integer pageSize,
                              String category,String status);
    List<TryItem> findByPage(Integer pageNo, Integer pageSize);

    int add(TryItem tryItem);

    int modify(TryItem tryItem);



}
