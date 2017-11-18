package com.cgwas.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.user.entity.User;

public class UrlFilter implements Filter { 
    private String permitUrls[] = null; 
    private String gotoUrl = null; 
    
    @Autowired
	private IPrivilegeInfoService privilegeInfoService;
    
    /**
	 * 判断该用户是否拥有该菜单下对应的子菜单(功能)权限，若存在前端跳到返回的url指定页面
	 * @param data
	 * @param response
	 * @return
	 */

    
    public void destroy() { 
        // TODO Auto-generated method stub 
        permitUrls = null; 
        gotoUrl = null; 
    } 
    public void doFilter(ServletRequest request, ServletResponse response, 
            FilterChain chain) throws IOException, ServletException { 
        // TODO Auto-generated method stub 
        HttpServletRequest res=(HttpServletRequest) request; 
        HttpServletResponse resp=(HttpServletResponse)response; 
        if(!isPermitUrl(request)){ 
            if(filterCurrUrl(request)){ 
                System.out.println("--->请登录"); 
                resp.sendRedirect(res.getContextPath()+gotoUrl); 
                return; 
            } 
        } 
        System.out.println("--->允许访问"); 
        chain.doFilter(request, response); 
    } 
    public boolean filterCurrUrl(ServletRequest request){ 
        boolean filter=false; 
        HttpServletRequest res=(HttpServletRequest) request; 
        User user =(User) res.getSession().getAttribute("user"); 
        if(null==user) 
            filter=true; 
        return filter;  

    }       
    public boolean isPermitUrl(ServletRequest request) { 
        boolean isPermit = false; 
        String currentUrl = currentUrl(request); 
        if (permitUrls != null && permitUrls.length > 0) { 
            for (int i = 0; i < permitUrls.length; i++) { 
                if (permitUrls[i].equals(currentUrl)) { 
                    isPermit = true; 
                    break; 
                } 
            } 
        } 
        return isPermit; 
    }        
    //请求地址 
    public String currentUrl(ServletRequest request) {   
        HttpServletRequest res = (HttpServletRequest) request; 
        String task = request.getParameter("task"); 
        String path = res.getContextPath(); 
        String uri = res.getRequestURI(); 
        if (task != null) {// uri格式 xx/ser 
            uri = uri.substring(path.length(), uri.length()) + "?" + "task="
                    + task; 
        } else { 
            uri = uri.substring(path.length(), uri.length()); 
        } 
        System.out.println("当前请求地址:" + uri); 
        return uri; 
    } 
    public void init(FilterConfig filterConfig) throws ServletException { 
        // TODO Auto-generated method stub 
        String permitUrls = filterConfig.getInitParameter("permitUrls"); 
        String gotoUrl = filterConfig.getInitParameter("gotoUrl"); 
  
        this.gotoUrl = gotoUrl; 
  
        if (permitUrls != null && permitUrls.length() > 0) { 
            this.permitUrls = permitUrls.split(","); 
        } 
    } 
} 