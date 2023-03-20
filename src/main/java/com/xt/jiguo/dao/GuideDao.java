package com.xt.jiguo.dao;

import com.xt.jiguo.entity.Guide;
import com.xt.jiguo.entity.vo.GuideVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuideDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Guide record);

    int insertSelective(Guide record);

    Guide selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Guide record);

    int updateByPrimaryKey(Guide record);
    List<Guide> selectItems();
    List<GuideVo> selectByOrder(@Param("order") String order);




}