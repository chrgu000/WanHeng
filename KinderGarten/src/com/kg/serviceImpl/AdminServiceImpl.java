package com.kg.serviceImpl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kg.dao.AdminDao;
import com.kg.entity.Admin;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.Page;
import com.kg.service.AdminService;
import com.kg.util.MD5;
import com.kg.util.ResponseUtil;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
	@Resource
	private AdminDao dao;

	public void addAdmin(Admin admin) {
		dao.addAdmin(admin);
	}

	public Admin getAdminById(Integer id) {
		return dao.getAdminById(id);
	}

	public List<Admin> getAdminByPage(Page page) {
		return dao.getAdminByPage(page);
	}

	public List<String> getPermissions(String username) {
		return dao.getPermissions(username);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public Admin login(Admin admin) {
		return dao.login(admin);
	}

	public void updateAdmin(Admin admin) {
		dao.updateAdmin(admin);
	}

	public void updateAdmin(Admin admin, HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		MD5 md5=new MD5();
		try {
				admin.setPassword(md5.getMD5ofStr(admin.getPassword()));
				dao.updateAdmin(admin);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object=JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;
			md5=null;
		}
	}

	public void addAdmin(Admin admin, HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		MD5 md5=new MD5();
		try {
				Admin a=dao.getAdminByUsername(admin.getUsername());
				if(a!=null){
					returnInfo.setHasError(true);
					returnInfo.setErrInfo("该管理员已存在");
					return;
				}
				admin.setJoin_time(new Timestamp(System.currentTimeMillis()));
				admin.setPassword(md5.getMD5ofStr(admin.getPassword()));
				dao.addAdmin(admin);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object=JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;
			md5=null;
		}
	}

	public void deleteAdmin(String ids, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
				Map<String,String[]> map=new HashMap<String,String[]>();
				map.put("ids", ids.split(","));
				dao.deleteByIds(map);
				dao.deleteAdminGardenByIds(map);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object=JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;
		}
	}

	public void updPsd(String oldpassword, String password,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		MD5 md5=new MD5();
		if(md5.getMD5ofStr(oldpassword).equals(admin.getPassword())){
			admin.setPassword(md5.getMD5ofStr(password));
			dao.updateAdmin(admin);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("修改成功");
		}else{
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("旧密码输入错误");
		}
		object=JSONObject.fromObject(returnInfo);
		ResponseUtil.write(response, object);
	}

	public void addAdminGarden(Map<String, Object> map) {
		 dao.addAdminGarden(map);
		
	}

	public void deleteAdminGarden(Integer id) {
		dao.deleteAdminGarden(id);
		
	}

	public List<Integer> getGardenIds(Integer id) {
		return dao.getGardenIds(id);
	}

	 
}
