package com.xt.jiguo.dao;

import com.xt.jiguo.entity.GuideComment;

public interface GuideCommentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(GuideComment record);

    int insertSelective(GuideComment record);

    GuideComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GuideComment record);

    int updateByPrimaryKey(GuideComment record);
}