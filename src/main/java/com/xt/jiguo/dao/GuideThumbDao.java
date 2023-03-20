package com.xt.jiguo.dao;

import com.xt.jiguo.entity.GuideThumb;

public interface GuideThumbDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GuideThumb record);

    int insertSelective(GuideThumb record);

    GuideThumb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GuideThumb record);

    int updateByPrimaryKey(GuideThumb record);

    int selectCountByThumb(GuideThumb thumb);
}