package com.xt.jiguo.service.impl;

import com.github.pagehelper.PageHelper;
import com.xt.jiguo.dao.UserDao;
import com.xt.jiguo.entity.User;
import com.xt.jiguo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao dao;
    @Override
    public User getById(Integer id) {
        return dao.selectByPrimaryKey(id);
    }

    @Override
    public User login(User user) {
        return dao.selectByUser(user);
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(User user) {
        int cnt = dao.selectCountByPhone(user.getPhone());
        if (cnt>0){
            throw new RuntimeException("phone is exist");
        }
        cnt=dao.selectCountByName(user.getName());
        if (cnt>0){
            throw new RuntimeException("username or phone is exist");
        }
            return dao.insert(user);
    }

    @Override
    public int findByPhone(String phone) {
        return dao.selectCountByPhone(phone);
    }

    @Override
    public int findByName(String name) {
        return dao.selectCountByName(name);
    }

    @Override
    public List<User> getUsersByPage(Integer pageNo, Integer pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        return dao.selectUsers();
    }

    @Override
    public int delete(Integer id) {
        return dao.updateStatus(id,1,0);
    }

    @Override
    public int recover(Integer id) {
        return dao.updateStatus(id,0,1);
    }



}
