package com.dq.serviceImpl;

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

import com.dq.dao.SmallTypeDao;
import com.dq.entity.Admin;
import com.dq.entity.SmallType;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.Page;
import com.dq.service.SmallTypeService;
import com.dq.util.MD5;
import com.dq.util.ResponseUtil;
@Service("smalltypeService")
@Transactional
public class SmallTypeServiceImpl implements SmallTypeService {
	@Resource
	private SmallTypeDao dao;

	public List<SmallType> getByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void update(SmallType smalltype, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		MD5 md5 = new MD5();
		try {
			dao.update(smalltype);
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

	public void add(SmallType smalltype, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			dao.save(smalltype);
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
		}
	}

	public void delete(String ids, HttpServletResponse response)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		boolean a = true;
		try {
			Map<String, String[]> map = new HashMap<String, String[]>();
			String[] Ids = ids.split(",");
			map.put("ids", Ids);
			if (a == true) {
				dao.deleteByIds(map);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			} else if (a == false) {
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("不能删除自己所属角色");
			}
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("该信息有级联数据,不能删除");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public SmallType getById(Integer id) {
		return dao.getById(id);
	}

	public List<SmallType> getSmallTypeByBigTypeId(Integer bigTypeId) {
		return dao.getSmallTypeByBigTypeId(bigTypeId);
	}


}
