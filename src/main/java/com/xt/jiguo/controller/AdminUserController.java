package com.xt.jiguo.controller;

import com.github.pagehelper.PageInfo;
import com.xt.jiguo.entity.User;
import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("admin/user")
public class AdminUserController {
    @Resource
    private UserService service;
    @RequestMapping(path = "list",method = RequestMethod.GET)
    public JsonData getByPage(Integer pno,Integer psize){
        List<User> users=service.getUsersByPage(pno,psize);
        if (users.size()==0){
            return JsonData.fail(400,"查无数据");

        }
        PageInfo<User> info =new PageInfo<>(users,5);
        return JsonData.success(info);
    }
    @RequestMapping(path="delete",method = RequestMethod.DELETE)
    public JsonData delete(Integer id){
        int rt=service.delete(id);
        if (rt==0){
            return JsonData.fail(400,"删除失败");
        }
        return JsonData.success("删除成功");
    }
    @RequestMapping(path="recover",method = RequestMethod.PUT)
    public JsonData recover(Integer id){
        int rt=service.recover(id);
        if (rt==0){
            return JsonData.fail(400,"恢复失败");
        }
        return JsonData.success("恢复成功");
    }

}
