package com.xt.jiguo.dao;

import com.xt.jiguo.entity.TryApply;

public interface TryApplyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TryApply record);

    int insertSelective(TryApply record);

    TryApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TryApply record);

    int updateByPrimaryKey(TryApply record);
    int selectCountById(Integer id);
}