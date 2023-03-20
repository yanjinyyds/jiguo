package com.xt.jiguo.controller;

import com.xt.jiguo.entity.User;
import com.xt.jiguo.entity.po.JsonData;
import com.xt.jiguo.service.UserService;
import com.xt.jiguo.valid.UserLoginValidation;
import com.xt.jiguo.valid.UserRegisterValidation;

import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.LocaleResolver;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private MessageSource messageSource;
    @Resource
    private LocaleResolver localeResolver;
    @Resource
    private UserService service;

    @RequestMapping(path = "find",method = { RequestMethod.POST,RequestMethod.GET})
    public JsonData find(HttpSession session){
       User user= (User) session.getAttribute("user");
        if (user==null){
            return JsonData.fail(400,"尚未登录");
        }
        return JsonData.success(user);
    }

    @RequestMapping(path = "login",method ={ RequestMethod.POST,RequestMethod.GET})
    public JsonData login(String imgCode,@Validated(UserLoginValidation.class) User user, Errors errors, HttpSession session){
        if (errors.hasErrors()){
            return JsonData.fail(401,errors.getAllErrors().get(0).getDefaultMessage());
        }
        String sessionCode= (String) session.getAttribute("code");
        if (imgCode==null||!imgCode.equalsIgnoreCase(sessionCode)){
//            return JsonData.fail(400,"验证码错误");
        }
        User temp= service.login(user);
        if (temp==null){
            return JsonData.fail(400,"无此用户");
        }
        System.out.println(temp.getName());
        session.setAttribute("user",temp);
        return JsonData.success(temp);
    }


    @RequestMapping(path = "logoff",method = RequestMethod.GET)
    public JsonData logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();
        return JsonData.success("ok");
    }



    @RequestMapping(path = "find/phone/{phone}",method = RequestMethod.GET)
    public JsonData findPhone(@PathVariable("phone") String phone){
        int cnt= service.findByPhone(phone);
        if (cnt==0){
            return JsonData.success("not exist");
        }
        return JsonData.success("exist");
    }
    @RequestMapping(path = "find/name/{name}",method = RequestMethod.GET)
    public JsonData findName(@PathVariable String name){
        int cnt= service.findByName(name);
        if (cnt==0){
            return JsonData.success("not exist");
        }
        return JsonData.success("exist");
    }

    @RequestMapping(path = "reg",method = RequestMethod.POST)
    public JsonData register(@Validated(UserRegisterValidation.class) User user, Errors errors,
                             String confirmPassword, HttpServletRequest request){
        if (errors.hasErrors()){
            String filed=((FieldError)(errors.getAllErrors().get(0))).getObjectName();
            if ("password".equals(filed)){
                return JsonData.fail(401,/*"密码不能为空");*/
                        messageSource.getMessage("user.reg.password",null
                                ,"password is required",
                                localeResolver.resolveLocale(request)));

            }
            return JsonData.fail(401,errors.getAllErrors().get(0).getDefaultMessage());
        }
        if(! user.getPassword().equals(confirmPassword)){
            return JsonData.fail(402,/*"二次密码必须相同");*/
                    messageSource.getMessage("user.reg.confirmPassword",
                    new Object[]{confirmPassword,user.getPassword()},
                    localeResolver.resolveLocale(request)));
        }

        try{
            service.register(user);
            return JsonData.success(user);
        }catch (Exception e){
            return JsonData.fail(400,e.getMessage());
        }

    }

}
