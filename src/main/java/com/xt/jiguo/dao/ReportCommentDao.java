package com.xt.jiguo.dao;

import com.xt.jiguo.entity.ReportComment;

public interface ReportCommentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportComment record);

    int insertSelective(ReportComment record);

    ReportComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportComment record);

    int updateByPrimaryKey(ReportComment record);
    int selectCountById(Integer id);
}