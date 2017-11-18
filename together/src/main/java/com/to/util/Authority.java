package com.to.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import java.util.List;

public class Authority {
	/**
	 * 判断是否有该权限
	 * 
	 * @param url
	 * @return
	 */
	public static boolean hasAuthority(String url) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		List<String> auths = (List<String>) session.getAttribute("auths"); // 获取菜单列表
		if (auths == null || auths.size() <= 0) {
			return false;
		}
		for (String auth : auths) {
			if (auth != null && auth.equals(url)) {
				return true;
			}
		}
		return false;
	}
}
