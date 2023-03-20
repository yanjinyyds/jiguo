package com.xt.jiguo.service;

import com.xt.jiguo.entity.Guide;
import com.xt.jiguo.entity.vo.GuideVo;

import java.util.List;

public interface GuideService {
    List<GuideVo> findByPage(Integer pageNo, Integer pageSize, String order);
    List<Guide> findByPage(Integer pageNo, Integer pageSize);
    int add(Guide guide);
    int modify(Guide guide);



}
