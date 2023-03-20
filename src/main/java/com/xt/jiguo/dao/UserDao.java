package com.xt.jiguo.dao;

import com.xt.jiguo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    User selectByApplyId(Integer applyId);

    User selectByUser(User user);
    int selectCountByPhone(String phone);

    /**
     *
     * @param name
     * @return
     */
    int selectCountByName(String name);
    List<User> selectUsers();

    int updateUser(User user);

    int updateStatus(@Param("id") Integer id,
                     @Param("oldState") Integer oldState ,
                     @Param("newState")Integer newState);

}