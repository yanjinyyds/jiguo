package com.xt.jiguo.interceptor;

import com.alibaba.fastjson.JSON;
import com.xt.jiguo.entity.Admin;
import com.xt.jiguo.entity.User;
import com.xt.jiguo.entity.po.JsonData;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    private void writeJson(HttpServletResponse response, JsonData data)throws Exception{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(JSON.toJSONString(data));
        out.close();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       /*boolean isAjax=false;
       if (request.getHeader("X-Requested-With")!=null){
           isAjax=true;
       }*/

        HttpSession session=request.getSession();
        Admin admin=(Admin) session.getAttribute("admin");
        if (admin==null){
            /*if (isAjax){*/
                writeJson(response,JsonData.fail(500,"请先登录"));
            /*}else {
                response.sendRedirect("/admin/login.html");
            }*/

            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
