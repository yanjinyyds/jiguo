package com.xt.jiguo.controller;

import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.entity.vo.CategoryVo;
import com.xt.jiguo.service.CoolCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("category")
public class CoolCategoryController {
    @Resource
    private CoolCategoryService service;

    @RequestMapping(path = "find",method = RequestMethod.GET)
    public JsonData find(){
        List<CategoryVo> vos=service.getCateories();
        if (vos.size()==0){
            return JsonData.fail(400,"查无数据");
        }
        return JsonData.success(vos);

    }

}
