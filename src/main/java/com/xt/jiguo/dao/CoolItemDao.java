package com.xt.jiguo.dao;

import com.xt.jiguo.entity.CoolItem;
import com.xt.jiguo.entity.vo.CoolItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoolItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CoolItem record);

    int insertSelective(CoolItem record);

    CoolItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoolItem record);

    int updateByPrimaryKey(CoolItem record);

    List<CoolItemVo> selectByOrder(@Param("order") String order);

    List<CoolItem> selectItems();

}
