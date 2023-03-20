package com.xt.jiguo.controller;

import com.github.pagehelper.PageInfo;
import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.entity.vo.TryReportVo;
import com.xt.jiguo.service.TryReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("report")
public class TryReportController {
    @Resource
    private TryReportService service;

    @RequestMapping(path = "find",method = RequestMethod.GET)
    public JsonData find(Integer pno,Integer psize,String order){
        List<TryReportVo> vos=service.findByPage(pno, psize, order);
        if (vos.size()==0){
            return JsonData.fail(400,"查无数据");
        }
        PageInfo<TryReportVo> info=new PageInfo<>(vos);
        return JsonData.success(info);

    }

}
