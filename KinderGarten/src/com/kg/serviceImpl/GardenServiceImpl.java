package com.kg.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kg.dao.BabyDao;
import com.kg.dao.GardenDao;
import com.kg.dao.TeacherDao;
import com.kg.entity.Baby;
import com.kg.entity.Garden;
import com.kg.entity.Teacher;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.Page;
import com.kg.service.GardenService;
import com.kg.util.MD5;
import com.kg.util.ResponseUtil;

@Service("gardenService")
@Transactional
public class GardenServiceImpl implements GardenService {
	@Resource
	private GardenDao dao;
	@Resource
	private TeacherDao tdao;
	@Resource
	private BabyDao bdao;
 
	public void addGarden(Garden garden) {
		dao.addGarden(garden);
	}

	public List<Garden> getAllGarden() {
		return dao.getAllGarden();
	}

	public Garden getGardenById(Integer id) {
		return dao.getGardenById(id);
	}

	public List<Garden> getGardenByPage(Page page) {
		return dao.getGardenByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateGarden(Garden garden) {
		dao.updateGarden(garden);
	}

	public void addGarden(Garden garden, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		MD5 md5 = new MD5();
		try {
			dao.addGarden(garden);
		
			JSONObject obj=JSONObject.fromObject(garden);
			Map<String,String> map=new HashMap<String,String>();
			map.put("garden"+garden.getId(), obj.toString());
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

	public void deleteGarden(String ids, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("ids", ids.split(","));
		String[] is=ids.split(",");
		try {
			List<Teacher> teachers = tdao.getTeacherByGardenIds(map);
			List<Baby> babys = bdao.getBabyByGardenIds(map);
			if ((teachers == null || teachers.size() == 0)
					&& (babys == null || babys.size() == 0)) {
				dao.deleteByIds(map);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			} else {
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("该园区有教师,学生级联数据,无法删除");
			}
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

	public void updateGarden(Garden garden, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		MD5 md5 = new MD5();
		try {
			dao.updateGarden(garden);
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

	public List<Garden> getGardensByAdminId(Integer id) {
		return dao.getGardensByAdminId(id);
	}
}
