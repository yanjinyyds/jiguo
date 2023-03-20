package com.xt.jiguo.controller;

import com.xt.jiguo.entity.CoolItemThumb;
import com.xt.jiguo.entity.GuideThumb;
import com.xt.jiguo.entity.User;
import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.service.CoolItemThumbService;
import com.xt.jiguo.service.GuideThumbService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("thumb")
public class ThumbController {
    @Resource
    private CoolItemThumbService thumbService;
    @Resource
    private GuideThumbService guideThumbService;

    @RequestMapping(path = "cool/{id}",method ={ RequestMethod.POST,RequestMethod.GET})
    public JsonData coolThumb(@PathVariable Integer id, HttpSession session){
        User user=(User) session.getAttribute("user");
      try {
          CoolItemThumb thumb=new CoolItemThumb();
          thumb.setItemId(id);
          thumb.setUserId(user.getId());
          thumbService.add(thumb);
          return JsonData.success("ok");
      }catch (Exception e){
            return JsonData.fail(400, e.getMessage());
      }

    }
    @RequestMapping(path = "guide",method ={ RequestMethod.POST,RequestMethod.GET})
    public JsonData guideThumb(Integer id, HttpSession session){
        User user=(User) session.getAttribute("user");
        try {
            GuideThumb thumb=new GuideThumb();
            thumb.setGuideId(id);
            thumb.setUserId(user.getId());
            guideThumbService.add(thumb);
            return JsonData.success("ok");
        }catch (Exception e){
            return JsonData.fail(400, e.getMessage());
        }

    }

}
