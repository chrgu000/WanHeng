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
import com.dq.dao.AttrDao;
import com.dq.entity.Att;
import com.dq.entity.Attr;
import com.dq.entity.util.ReturnInfo;
import com.dq.service.AttrService;
import com.dq.util.DateUtil;
import com.dq.util.ResponseUtil;

@Service("attrService")
@Transactional
public class AttrServiceImpl implements AttrService {
	@Autowired
	private AttrDao attrDao;
	@Autowired
	private AttDao attDao;

	public Long getTotal(Map<String, Object> map) {
		return attrDao.getTotal(map);
	}

	public List<Attr> getByPage(Map<String, Object> map) {
		return attrDao.getByPage(map);
	}

	public List<Attr> getByMap(Map<String, Object> map) {
		return attrDao.getByMap(map);
	}

	public Attr findById(Integer id) {
		return attrDao.findById(id);
	}

	public Attr findByMap(Map<String, Object> map) {
		return attrDao.findByMap(map);
	}

	public List<Attr> getByPro(Map<String, Object> map) {
		return attrDao.getByPro(map);
	}

	public void addAttr(String dataid, String title, String v, String callback, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		ReturnInfo returnError = new ReturnInfo();
		JSONObject jsonObject = null;
		Att att = null;
		Attr attr = null;

		try {

			if (dataid == null || title == null || v == null || "".equals(dataid.trim()) || "".equals(title.trim())
					|| "".equals(v.trim())) {
				returnError.setHasError(true);
				returnError.setErrType("itemnull");
				returnError.setErrInfo("新建失败，数据不全");
				jsonObject = JSONObject.fromObject(returnError);
				ResponseUtil.write(response, callback, jsonObject);
				return;
			}
			map.put("attr_title", title.trim());
			att = attDao.findByMap(map);
			// if (att != null) {
			// returnError.setHasError(true);
			// returnError.setErrType("itemerror");
			// returnError.setError("请勿重复创建SKU型号");
			// jsonObject = JSONObject.fromObject(returnError);
			// ResponseUtil.write(response, callback, jsonObject);
			// return;
			// }
			if (att != null) {
				// if (!dataid.startsWith("new") &&
				// !dataid.equals(att.getAtt_id().toString())) {
				// returnError.setHasError(true);
				// returnError.setErrType("itemerror");
				// returnError.setError("SKU型号不匹配");
				// jsonObject = JSONObject.fromObject(returnError);
				// ResponseUtil.write(response, callback, jsonObject);
				// return;
				// }
				att.setDelflag(1);
				attDao.upd(att);
				map.clear();
				map.put("att_id", att.getAtt_id());
				map.put("v", v.trim());
				attr = attrDao.findByMap(map);
				if (attr != null) {
					attr.setDelflag(1);
					attrDao.upd(attr);
					returnError.setHasError(false);
					returnError.setErrType("success");
					returnError.setObject(attr);
					jsonObject = JSONObject.fromObject(returnError);
					ResponseUtil.write(response, callback, jsonObject);
					return;
				}
				attr = new Attr();
				attr.setAtt_id(att.getAtt_id());
				attr.setV(v.trim());
				attr.setAddtime(DateUtil.getCurrentDateStr());
				attr.setDelflag(1);
				attrDao.add(attr);
			} else {
				att = new Att();
				att.setAttr_title(title.trim());
				att.setAddtime(DateUtil.getCurrentDateStr());
				att.setDelflag(1);
				attDao.add(att);

				attr = new Attr();
				attr.setAtt_id(att.getAtt_id());
				attr.setV(v.trim());
				attr.setAddtime(DateUtil.getCurrentDateStr());
				attr.setDelflag(1);
				attrDao.add(attr);
			}
			returnError.setHasError(false);
			returnError.setErrType("success");
			returnError.setObject(attr);
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
			att = null;
			attr = null;
		}
	}

	public void delAttr(String a, String dataid, String datatitle, String callback, HttpServletResponse response)
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
			// 删除sku型号子元素
			map.put("delflag", 2);
			map.put("v", datatitle.trim());
			attrDao.updByTitle(map);
			returnError.setHasError(false);
			returnError.setErrType("success");
			returnError.setErrInfo("删除成功");
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

	public List<Attr> getAttr(Map<String, Object> map) {
		return attrDao.getAttr(map);
	}

}
