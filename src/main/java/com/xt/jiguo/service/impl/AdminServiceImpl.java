package com.xt.jiguo.service.impl;

import com.xt.jiguo.dao.AdminDao;
import com.xt.jiguo.entity.Admin;
import com.xt.jiguo.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao dao;


    @Override
    public Admin login(Admin admin) {
        return dao.selectByAdmin(admin);
    }
}
