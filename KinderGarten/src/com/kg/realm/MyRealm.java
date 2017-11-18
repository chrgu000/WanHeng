package com.kg.realm;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.kg.entity.Admin;
import com.kg.service.AdminService;

public class MyRealm extends AuthorizingRealm {
//	@Resource
//	private RoleService roleService;
	@Resource
	private AdminService adminService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		 System.out.println("dddd");
//		 String userName = (String) principals.getPrimaryPrincipal();
//		 SimpleAuthorizationInfo authorizationInfo = new
//		 SimpleAuthorizationInfo();
//		 Set<String> roles = adminService.getRoles(userName);
//		 System.out.println("dddd" + roles.size());
//		 Set<String> permissions = (Set<String>) adminService.getPermissions(userName);
//		 System.out.println("ddddfff" + permissions.size());
//		 authorizationInfo.setRoles(roles);
//		 authorizationInfo.setStringPermissions(permissions);
//		 return authorizationInfo;
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// System.out.println("d");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		Admin admin = new Admin(), admin2 = null;
		admin.setUsername(token.getUsername());
		admin2 = adminService.login(admin);
		if (admin2 != null) {
			return new SimpleAuthenticationInfo(admin2.getUsername(), admin2.getPassword(), getName());
		} else {
			throw new AuthenticationException();
		}
	}
}
