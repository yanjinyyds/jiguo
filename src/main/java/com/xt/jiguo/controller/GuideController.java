package com.xt.jiguo.controller;

import com.github.pagehelper.PageInfo;
import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.entity.vo.GuideVo;
import com.xt.jiguo.service.GuideService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("guide")
public class GuideController {
    @Resource
    private GuideService service;

    @RequestMapping(path = "find",method = RequestMethod.GET)
    public JsonData find(Integer pno,Integer psize,String order){
        List<GuideVo> vos = service.findByPage(pno, psize, order);
        if (vos.size()==0){
            return JsonData.fail(400,"查无数据");
        }
        PageInfo<GuideVo> info=new PageInfo<>(vos);
        return JsonData.success(info);

    }

}
