package com.cgwas.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;


public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("======处理请求之前======");
        request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json;charset=UTF-8");
		Object user=request.getSession().getAttribute("loginUser");
		System.out.println("chat:"+user);
		System.out.println(request.getRequestURI());
        if(user==null){
        	System.out.println("您的登录失效，请重新登录");
        	JSONObject json = new JSONObject();
        	json.put("state", false);
        	json.put("message", "您的登录失效，请重新登录！");
        	json.put("errorNo", "RY0008");
        	response.getWriter().print(json);
        	return false;
        }
        return true;
    }

    @Override
    public void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
        //System.out.println("========处理请求后，渲染页面前======");
        //modelAndView.addObject("post","interceptor change view before rendering");
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //System.out.println("========视图渲染结束了，请求处理完毕====");
    }
}
