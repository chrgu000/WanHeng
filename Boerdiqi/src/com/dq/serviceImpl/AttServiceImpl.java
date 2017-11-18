package com.dq.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.AttDao;
import com.dq.entity.Att;
import com.dq.entity.util.ReturnInfo;
import com.dq.service.AttService;
import com.dq.util.ResponseUtil;

@Service("attService")
@Transactional
public class AttServiceImpl implements AttService {

	@Autowired
	private AttDao attDao;

	public Long getTotal(Map<String, Object> map) {
		return attDao.getTotal(map);
	}

	public List<Att> getByPage(Map<String, Object> map) {
		return attDao.getByPage(map);
	}

	public List<Att> getByMap(Map<String, Object> map) {
		return attDao.getByMap(map);
	}

	public Att findById(Integer id) {
		return attDao.findById(id);
	}

	public Att findByMap(Map<String, Object> map) {
		return attDao.findByMap(map);
	}

	public List<Att> getByPro(Map<String, Object> map) {
		return attDao.getByPro(map);
	}

	public void delAtt(String a, String dataid, String datatitle, String callback, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ReturnInfo returnError = new ReturnInfo();
		JSONObject jsonObject = null;
		// Att att = null;
		try {
			if (datatitle == null || "".equals(datatitle.trim())) {
				returnError.setHasError(true);
				returnError.setErrType("itemnull");
				returnError.setErrInfo("删除失败");
				jsonObject = JSONObject.fromObject(returnError);
				ResponseUtil.write(response, callback, jsonObject);
				return;
			}
			// 删除sku型号
			map.put("adelflag", 2);
			map.put("ardelflag", 2);
			map.put("attr_title", datatitle.trim());
			attDao.updByTitle(map);
			returnError.setHasError(false);
			returnError.setErrType("success");
			returnError.setErrInfo("删除成功");
			returnError.setObject(dataid);
			jsonObject = JSONObject.fromObject(returnError);
			ResponseUtil.write(response, callback, jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			returnError.setHasError(true);
			returnError.setErrType("error");
			returnError.setErrInfo("系统异常");
			jsonObject = JSONObject.fromObject(returnError);
			ResponseUtil.write(response, callback, jsonObject);
			throw new RuntimeException();
		} finally {
			map = null;
			returnError = null;
			jsonObject = null;
		}
	}

	public List<Att> getAtt(Map<String, Object> map) {
		return attDao.getAtt(map);
	}

}
