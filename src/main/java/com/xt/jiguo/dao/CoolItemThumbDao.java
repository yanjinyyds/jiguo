package com.xt.jiguo.dao;

import com.xt.jiguo.entity.CoolItemThumb;

public interface CoolItemThumbDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CoolItemThumb record);

    int insertSelective(CoolItemThumb record);

    CoolItemThumb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoolItemThumb record);

    int updateByPrimaryKey(CoolItemThumb record);

    int selectCountById(Integer id);

    int selectCountByThumb(CoolItemThumb thumb);



}