package com.jxc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.jxc.dao.TitleDao;
import com.jxc.entity.Admin;
import com.jxc.entity.Power;
import com.jxc.entity.Title;
import com.jxc.page.Page;
import com.jxc.serviceImpl.AdminServiceImpl;
import com.jxc.serviceImpl.PowerServiceImpl;
import com.jxc.util.MD5;

@Controller
@RequestMapping("/admin")
@Scope("prototype")
@SessionAttributes("page")
public class AdminController {
	@Resource
	private AdminServiceImpl service;//����Ա�������
	@Resource 
	private PowerServiceImpl pservice;//��ɫ�������
	@Resource
	private TitleDao dao;
	@RequestMapping("/login.do")
	public String loginAction(Admin admin, HttpServletRequest request,
			HttpSession session) {
		MD5 md5 = new MD5();
		Admin admin1=null;
		if(admin!=null&&admin.getPassword()!=null){
			admin.setPassword(md5.getMD5ofStr(admin.getPassword()));// ���������Ĵ���һ��
			admin1 = service.login(admin);// ��¼��ȡ���¼��ֵ��admin����
		}
		if (admin1 == null) {
			request.setAttribute("message", "�û������������");
			return "admin/login";//ת������¼ҳ��
		}
		List<Title> titles=dao.findAllTitle();
		session.setAttribute("admin", admin1);//session�����¼�ߵ���Ϣ
		session.setAttribute("titles", titles);//session���������Ϣ
		return "redirect:../admin/index.jsp";//�ض��򵽹���Ա��ҳ
	}

	@RequestMapping("/toAddAdmin.do")
	public String toAddAction(ModelMap map) {
		List<Power> powers=pservice.findAllPower();//��ȡ���е�Ȩ��
		List<Admin> admins=service.findAllAdmin();//��ȡ���еĹ���Ա
		List<String> names=new ArrayList<String>();
		for (Admin admin: admins) {
			names.add(admin.getUsername());//��ȡ���й���Ա���������϶���
		}
		map.put("names",names.toString());
		map.put("powers",powers);
		return "admin/admin_add";//ת��������Ա���ҳ��
	}

	@RequestMapping("/addAdmin.do")
	public String addAction(Admin admin) { 
		MD5 md5=new MD5();
		admin.setPassword(md5.getMD5ofStr(admin.getPassword()));//��MD5�ļ��ܼ�����������м���
       service.addAdmin(admin);//��ӹ���Ա
       return "redirect:../admin/findAllAdminByPage.do";//�ض��򵽹���Ա����ҳ
	}
	@RequestMapping("/toUpdateAdmin.do")
	public String toUpdateAction(ModelMap map,String username) {
		Admin admin = service.findAdminByUsername(username);//�����û�����ѯ����Ա����
		map.put("admin", admin);
		return "admin/admin_update";//ת��������Ա�޸�ҳ��
	}

	@RequestMapping("/updateAdmin.do")
	public String updateAction(Admin admin) {
		MD5 md5=new MD5();
		admin.setPassword(md5.getMD5ofStr(admin.getPassword()));//MD5������������м���
		if (service.updateAdmin(admin)) {//�޸Ĺ���Ա��Ϣ
			return "redirect:../admin/ findAllAdminByPage.do";//�ض������Ա��ҳ
		}
		return "admin/toUpdateAdmin.do?username=" + admin.getUsername();//ת�����޸Ĺ���Ա��Ϣҳ��
	}

	@RequestMapping("/deleteAdmin.do")
	public String deleteAction(Integer id) {
		service.deleteAdminById(id);//����idɾ������Ա��Ϣ
			return "redirect:../admin/ findAllAdminByPage.do";//�ض��򵽹���Ա��ҳ
	}

	@RequestMapping("/findAllAdminByPage.do")
	public String findAllAction(ModelMap map, Page page) {
		int rows=service.findRows();//��ȡ���ݱ����ݵ�����
		page.setRows(rows);
		List<Admin> admins = service.findAllAdminByPage(page);//��ҳ��ѯ���й���Ա����Ϣ
		map.put("page",page);
		map.put("admins", admins);
		return "admin/admin";//ת��������Ա��ҳ
	}
}
