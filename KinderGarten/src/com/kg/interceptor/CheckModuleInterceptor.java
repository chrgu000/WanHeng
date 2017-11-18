package com.kg.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kg.entity.util.ReturnInfo;
import com.kg.util.ResponseUtil;

public class CheckModuleInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		List<String> auths = (List<String>) session.getAttribute("auths");
		String Url = request.getRequestURI();
		String action = Url.substring(Url.lastIndexOf("/"), Url
				.lastIndexOf("."));
		boolean flag = false;
		boolean m = false;
		for (String url : auths) {
			if (url.indexOf(action) > -1) {
				flag = true;
				break;
			}
		}
		if (action.indexOf("load") > -1) {
			m = true;
		}
		ReturnInfo returnInfo = new ReturnInfo();
		if (!flag && !m) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("你没有操纵权限");
			JSONObject object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			return false;
		} else if (!flag && m) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("hasError", true);
			map.put("error", "你没有操纵权限");
			map.put("rows", "");
			map.put("results", 0);
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			return false;
		}
		return true;
	}

}
