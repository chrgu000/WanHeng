package com.kg.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kg.dao.LogDao;
import com.kg.entity.Log;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.Page;
import com.kg.service.LogService;
import com.kg.util.Authority;
import com.kg.util.ResponseUtil;

@Service("logService")
@Transactional
public class LogServiceImpl implements LogService {
	@Resource
	private LogDao dao;

	public void deleteLog(String ids, HttpServletResponse response, String flag)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		boolean check = false;
		try {
			if (flag != null && flag.equals("0")) {
				check = Authority.hasAuthority("/log/stu_deleteLog.do");
			} else {
				check = Authority.hasAuthority("/log/tea_deleteLog.do");
			}
			if (check == true) {
				Map<String, String[]> map = new HashMap<String, String[]>();
				map.put("ids", ids.split(","));
				dao.deleteByIds(map);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			} else {
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("没有该操纵权限");
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

	public List<Log> getLogByPage(Page page) {
		return dao.getLogByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateLog(Log log, HttpServletResponse response, String flag)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		boolean check = false;
		try {
			if (flag != null && flag.equals("0")) {
				check = Authority.hasAuthority("/log/stu_updLog.do");
			} else {
				check = Authority.hasAuthority("/log/tea_updLog.do");
			}
			if (check == true) {
				dao.updateLog(log);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			} else {
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("没有该操纵权限");
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

	public Log getLogById(Integer id) {
		return dao.getLogById(id);
	}

	public void updateLog(Log log) {
		dao.updateLog(log);
	}

	public List<Log> getLogsByBabyId(Integer babyId) {
		return dao.getLogsByBabyId(babyId);
	}

	public void updateLog(Log log, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			dao.updateLog(log);
			returnInfo.setHasError(false);
			if(log.getReply()==null||log.getReply().trim()==""){
				returnInfo.setErrInfo("保存成功");
			}else{
				returnInfo.setErrInfo("成功提交评论回复");
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

	public void addLog(Log log, HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			log.setState(0);
			log.setFlag(0);
			dao.addLog(log);
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

	public List<Log> getLogsByBabyId1(Integer babyId) {
		return dao.getLogsByBabyId1(babyId);
	}

}
