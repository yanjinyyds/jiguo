package com.xt.jiguo.controller;

import com.github.pagehelper.PageInfo;
import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.entity.vo.TryItemVo;
import com.xt.jiguo.service.TryItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("tryitem")
public class TryItemController {
    @Resource
    private TryItemService service;

    @RequestMapping(path = "find",method = RequestMethod.GET)
    public JsonData find(Integer pno,Integer psize,String category,String status){
        List<TryItemVo> vos=service.getByPage(pno,psize,category,status);
        if (vos.size()==0){
            return JsonData.fail(400,"查无数据");
        }
        PageInfo<TryItemVo> info=new PageInfo<>(vos);
        return JsonData.success(info);

    }

}
