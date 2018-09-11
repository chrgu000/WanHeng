package com.cgwas.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cgwas.common.utils.DataUtils;

public class TimeInteceptor implements HandlerInterceptor {

	// 在实际的处理程序将被执行
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		long startTime = System.currentTimeMillis();
		String requestUri = request.getRequestURI();

		System.out.println("----------开始时间:" + DataUtils.timeStamp2Date(DataUtils.timeStamp() + "", DataUtils.sdfyyyyMMddHHmmss) + "----------");
		System.out.println("----------开始方法:" + (request.getParameter("action") == null ? "" :request.getParameter("action"))+ "----------");
		System.out.println("----------开始地址:" + requestUri + "----------");
		request.setAttribute("interceptorStartTime", startTime);
		return true;
	}

	// 处理程序后执行
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

		long startTime = (Long) request.getAttribute("interceptorStartTime");

		long endTime = System.currentTimeMillis();

		long executeTime = endTime - startTime;

		String requestUri = request.getRequestURI();

		System.out.println("----------结束方法:" + (request.getParameter("action") == null ? "" :request.getParameter("action"))+ "----------");
		System.out.println("----------结束地址:" + requestUri + "----------");
		System.out.println("----------结束时间:" + DataUtils.timeStamp2Date(DataUtils.timeStamp() + "", DataUtils.sdfyyyyMMddHHmmss) + "----------");
		System.out.println("----------运行时间:" + executeTime + "毫秒----------");
		System.out.println();
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}

}
