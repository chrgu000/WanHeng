package com.to.serviceImpl;


import com.to.dao.RoleDao;
import com.to.entity.Admin;
import com.to.entity.Role;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.RoleService;
import com.to.util.MD5;
import com.to.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
@Resource
private RoleDao dao;
	public List<Role> getRoleByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateRole(Role role, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		MD5 md5=new MD5();
		try {   role.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(role);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object= JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;
			md5=null;
		}		
	}

	public void addRole(Role role, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
				Role r=dao.getRoleByName(role.getName());
				if(r!=null){
					returnInfo.setHasError(true);
					returnInfo.setErrInfo("该角色已存在");
					return;
				}
			    role.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			    role.setCreateTime(new Timestamp(System.currentTimeMillis()));
				dao.save(role);
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
		}
	}

	public void deleteRole(String ids, HttpServletResponse response)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Admin admin=(Admin) session.getAttribute("admin");
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		boolean a=true;
		try {
			Map<String,String[]> map=new HashMap<String,String[]>();
			String[] Ids=ids.split(",");
			for (String id : Ids) {
				if(admin.getRole().getId().equals(id)){
					a=false;
					break;
				}
			}
			map.put("ids", Ids);
			if(a==true){
				dao.deleteByIds(map);
				dao.deleteRoleModule(map);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			}else if(a==false){
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("不能删除自己所属角色");
			} 
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

	public Role getRoleById(Integer id) {
		return dao.getById(id);
	}

	public List<Role> getAllRole() {
		return dao.getAllRole();
	}

}
