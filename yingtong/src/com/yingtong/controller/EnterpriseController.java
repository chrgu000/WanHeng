package com.yingtong.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.yingtong.entity.Enterprise;
import com.yingtong.page.EnterprisePage;
import com.yingtong.serviceImpl.EnterpriseServiceImpl;
import com.yingtong.util.ImageUtil;

@Controller
@Scope("prototype")
@RequestMapping("/enterprise")
@SessionAttributes("enterprisePage")
public class EnterpriseController {
@Resource
private EnterpriseServiceImpl service;//企业服务对象
@RequestMapping("/toAddEnterprise.do")
public String toAddAction(ModelMap map){
	List<Enterprise> enterprise=service.findAllEnterprises();
	List<String> tels=new ArrayList<String>();
	for (Enterprise enterprise2 : enterprise) {
		tels.add(enterprise2.getTel());//获取企业账户下的所有手机号
	}
	map.put("tels", tels);
	return "index/enterprise_apply";
}
@RequestMapping("/toAddEnterprise1.do")
public String toAdd1Action(ModelMap map){
	List<Enterprise> enterprise=service.findAllEnterprises();
	List<String> tels=new ArrayList<String>();
	for (Enterprise enterprise2 : enterprise) {
		tels.add(enterprise2.getTel());//获取企业账户下的所有手机号
	}
	map.put("tels", tels);
	return "mobile/qiye";
}
@RequestMapping("/addEnterprise.do")
public String addAction(Enterprise enterprise,String code,HttpSession  session,ModelMap map){
	String code1=session.getAttribute("code").toString();
	if(code!=null&&code.equals(code1)){
		Timestamp apply_time=new Timestamp(System.currentTimeMillis());
		enterprise.setApply_time(apply_time);
		service.addEnterprise(enterprise);//申请企业账户添加
  		map.put("message","success");
		return "index/enterprise_apply";
	}else{
      		map.put("message","验证码错误!");
      		map.put("e",enterprise);
		return "index/enterprise_apply";
	}
}
@RequestMapping("/addEnterprise1.do")
public String add1Action(Enterprise enterprise,ModelMap map){
		Timestamp apply_time=new Timestamp(System.currentTimeMillis());
		enterprise.setApply_time(apply_time);
		service.addEnterprise(enterprise);//申请企业账户添加
		return "redirect:../mobile/qiyecg.jsp";
 
}
@RequestMapping("/enterprise_list.do")
public String findAllAction(ModelMap map,EnterprisePage page){
	Integer rows=service.findRows(page);
	page.setRows(rows);
	map.addAttribute("enterprisePage",page);
	List<Enterprise> enterprises=service.findAllEnterpriseByPage(page);
	map.put("enterprises",enterprises);
	return "admin/enterprise";
}
@RequestMapping("/deleteEnterpriseById.do")
public String deleteAction(Integer id){
	service.deleteEnterpriseById(id);
	return "redirect:../enterprise/enterprise_list.do";
}
@RequestMapping("/createImage.do")
public void getImage(HttpSession session, HttpServletResponse response,
		HttpServletRequest request) throws IOException {
	Map<String, Object> map = ImageUtil.createImage();
	BufferedImage image = (BufferedImage) map.get("image");
	String code = (String) map.get("code");
	session.setAttribute("code", code);
	response.setContentType("image/png");
	OutputStream ops = response.getOutputStream();
	javax.imageio.ImageIO.write(image, "png", ops);//图片验证码的生成
	ops.close();
}
}
