package com.xt.jiguo.controller;

import com.github.pagehelper.PageInfo;
import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.entity.vo.CoolItemVo;
import com.xt.jiguo.service.CoolItemService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author: 86136
 * @date:
 * @version: 1.0
 * @description:  pno当前页, psize当前页的记录数,order筛选规则hot按热度排序，其他的按照最新排序
 * http://localhost:8000/swagger-ui.html访问swagger在线api文档
 */
@RestController
@RequestMapping("cool")
/*@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST}
)*/
public class CoolItemController {
    @Resource
    private CoolItemService service;

    @RequestMapping(path = "find",method = RequestMethod.GET)
    public JsonData find(Integer pno,Integer psize,String order){
        List<CoolItemVo> vos=service.findByPage(pno, psize, order);
        //如果得到的集合为空
        if (vos.size()==0){
            return JsonData.fail(400,"查无数据");
        }
        //封装返回给前端带有分页信息的pageInfo对象
        PageInfo<CoolItemVo> info=new PageInfo<>(vos);
        return JsonData.success(info);
    }

}
