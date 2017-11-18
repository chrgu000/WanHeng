package com.to.serviceImpl;


import com.to.dao.UserDao;
import com.to.entity.User;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.UserService;
import com.to.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {
@Resource
private UserDao dao;
	public List<User> getUserByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateUser(User user, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;

		try {   user.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(user);
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

		}		
	}

	public void addUser(User user, HttpServletResponse response)
			throws Exception {
				dao.save(user);
	}

	public void deleteUser(String ids, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;

		try {
			Map<String,String[]> map=new HashMap<String,String[]>();
			String[] Ids=ids.split(",");
			map.put("ids", Ids);
			dao.deleteByIds(map);
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

	@Override
	public List<String> findAllTel() {
		return dao.findAllTel();
	}

	@Override
	public User login(User user) {
		return dao.login(user);
	}

	@Override
	public void updatePwdByTel(User user) {
		dao.updatePwdByTel(user);
	}

	@Override
	public List<String> findAllAreaByCity(String city) {
		return dao.findAllAreaByCity(city);
	}

	public User getUserById(Integer id) {
		return dao.getById(id);
	}



}
