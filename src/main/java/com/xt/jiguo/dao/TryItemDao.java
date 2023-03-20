package com.xt.jiguo.dao;

import com.xt.jiguo.entity.TryItem;
import com.xt.jiguo.entity.vo.TryItemVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TryItemDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TryItem record);

    int insertSelective(TryItem record);

    TryItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TryItem record);

    int updateByPrimaryKey(TryItem record);
    String selectNameByApplyId(Integer id);
    List<TryItemVo> selectByCategoryAndStatus(
            @Param("category") String category,
            @Param("status") String status);
}