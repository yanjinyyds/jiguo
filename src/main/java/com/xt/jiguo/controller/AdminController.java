package com.xt.jiguo.controller;

import com.xt.jiguo.entity.Admin;
import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.service.AdminService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private AdminService service;
    @RequestMapping(path = "find",method = { RequestMethod.POST,RequestMethod.GET})
    public JsonData find(HttpSession session){
        Admin admin= (Admin) session.getAttribute("admin");
        if (admin==null){
            return JsonData.fail(400,"尚未登录");
        }
        return JsonData.success(admin);
    }

    @RequestMapping(path = "login",method ={ RequestMethod.POST,RequestMethod.GET})
    public JsonData login(String imgCode, Admin admin, HttpSession session){

        String sessionCode= (String) session.getAttribute("code");
        if (imgCode==null||!imgCode.equalsIgnoreCase(sessionCode)){
//            return JsonData.fail(400,"验证码错误");
        }
        Admin temp= service.login(admin);
        if (temp==null){
            return JsonData.fail(400,"无此用户");
        }
        System.out.println(temp.getName());
        session.setAttribute("admin",temp);
        return JsonData.success(temp);
    }



    @RequestMapping(path = "logoff",method = RequestMethod.GET)
    public JsonData logout(HttpSession session){
        session.removeAttribute("admin");
        session.invalidate();
        return JsonData.success("ok");
    }





}
