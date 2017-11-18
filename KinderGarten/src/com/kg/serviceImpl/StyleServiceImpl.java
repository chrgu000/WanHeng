package com.kg.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kg.dao.StyleDao;
import com.kg.entity.Style;
import com.kg.entity.StylePic;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.Page;
import com.kg.service.StyleService;
import com.kg.util.ResponseUtil;

@Service("styleService")
@Transactional
public class StyleServiceImpl implements StyleService {
	@Resource
	private StyleDao dao;

	public void addStyle(Style style, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			dao.addStyle(style);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}

	}

	public void deleteByIds(String ids, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			Map<String, String[]> map = new HashMap<String, String[]>();
			map.put("ids", ids.split(","));
			dao.deleteByIds(map);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public Style getStyleById(Integer id) {
		return dao.getStyleById(id);
	}

	public List<Style> getStyleByPage(Page page) {
		return dao.getStyleByPage(page);
	}

	public List<Style> getStyleByTeacherId(Integer teacherId) {
		return dao.getStyleByTeacherId(teacherId);
	}

	public void updateStyle(Style style, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			dao.updateStyle(style);
			returnInfo.setHasError(false);
			returnInfo.setErrInfo("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		} finally {
			object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo = null;
			object = null;
		}

	}

	public void addStylePic(StylePic stylePic, HttpServletResponse response)
			throws Exception {
		dao.addStylePic(stylePic);
		
	}

}
