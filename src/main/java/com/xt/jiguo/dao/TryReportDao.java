package com.xt.jiguo.dao;

import com.xt.jiguo.entity.TryReport;
import com.xt.jiguo.entity.vo.TryReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TryReportDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TryReport record);

    int insertSelective(TryReport record);

    TryReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TryReport record);

    int updateByPrimaryKey(TryReport record);
    
    int selectCountById(Integer id);
    List<TryReportVo> selectByOrder(@Param("order") String order);
    int selectCountByReportId(Integer id);
}