package com.xt.jiguo.service;


import com.xt.jiguo.entity.User;

import java.util.List;

/**
 *
 */

public interface UserService {
    User getById(Integer id);

    User login(User user);

    int register(User user);

    int findByPhone(String phone);
    int findByName(String name);


    List<User> getUsersByPage(Integer pageNo, Integer pageSize);

    int delete(Integer id);
    int recover(Integer id);


}
