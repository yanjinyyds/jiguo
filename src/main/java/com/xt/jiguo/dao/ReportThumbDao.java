package com.xt.jiguo.dao;

import com.xt.jiguo.entity.ReportThumb;

public interface ReportThumbDao {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportThumb record);

    int insertSelective(ReportThumb record);

    ReportThumb selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportThumb record);

    int updateByPrimaryKey(ReportThumb record);
}