package com.xt.jiguo.dao;

import com.xt.jiguo.entity.CoolCategory;

import java.util.List;

public interface CoolCategoryDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CoolCategory record);

    int insertSelective(CoolCategory record);

    CoolCategory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoolCategory record);

    int updateByPrimaryKey(CoolCategory record);

    List<CoolCategory> selectCategories();


}