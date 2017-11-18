package com.dq.serviceImpl;

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

import com.dq.dao.AdminDao;
import com.dq.entity.Admin;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.Page;
import com.dq.service.AdminService;
import com.dq.util.MD5;
import com.dq.util.ResponseUtil;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {
	@Resource
	private AdminDao dao;
	public List<Admin> getByPage(Page page) {
		return dao.getAllByPage(page);
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

	public Admin getById(Integer id) {
		return dao.getById(id);
	}

	public void update(Admin admin, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		MD5 md5 = new MD5();
		try {
			admin.setPassword(md5.getMD5ofStr(admin.getPassword()));
			dao.update(admin);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
			md5 = null;
		}
	}

	public void add(Admin admin, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		MD5 md5 = new MD5();
		try {
			Admin a = dao.getAdminByUsername(admin.getUsername());
			if (a != null) {
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("该管理员已存在");
				return;
			}
			admin.setJoin_time(new Timestamp(System.currentTimeMillis()));
			admin.setPassword(md5.getMD5ofStr(admin.getPassword()));
			dao.save(admin);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
			md5 = null;
		}
	}

	public void delete(String ids, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			Map<String, String[]> map = new HashMap<String, String[]>();
			map.put("ids", ids.split(","));
			dao.deleteByIds(map);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("该信息有级联数据,不能删除");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public void updPsd(String oldpassword, String password,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		MD5 md5 = new MD5();
		if (md5.getMD5ofStr(oldpassword).equals(admin.getPassword())) {
			admin.setPassword(md5.getMD5ofStr(password));
			dao.update(admin);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("修改成功");
		} else {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("旧密码输入错误");
		}
		object = JSONObject.fromObject(returnInfo);
		ResponseUtil.write(response, object);
	}
}
