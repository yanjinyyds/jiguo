package com.xt.jiguo.interceptor;

import com.alibaba.fastjson.JSON;
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
public class AuthInterceptor implements HandlerInterceptor {
    private void writeJson(HttpServletResponse response, JsonData data)throws Exception{
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.print(JSON.toJSONString(data));
        out.close();

    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        User user=(User) session.getAttribute("user");
        if (user==null){
            writeJson(response,JsonData.fail(400,"请先登录"));
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
