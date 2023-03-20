package com.xt.jiguo.dao;

import com.xt.jiguo.entity.CoolItemComment;

public interface CoolItemCommentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CoolItemComment record);

    int insertSelective(CoolItemComment record);

    CoolItemComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoolItemComment record);

    int updateByPrimaryKey(CoolItemComment record);
}