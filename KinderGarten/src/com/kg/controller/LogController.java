package com.kg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kg.entity.Baby;
import com.kg.entity.Log;
import com.kg.entity.Teacher;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.LogPage;
import com.kg.service.LogService;
import com.kg.util.Authority;
import com.kg.util.CookieUtil;
import com.kg.util.ResponseUtil;

@Controller
@RequestMapping("/log")
@SessionAttributes("logPage")
public class LogController {
	private static final Logger log = Logger.getLogger(BabyController.class);
	@Resource
	private LogService service;
    private Integer logId;
	@RequestMapping("/loadLog.do")
	public void loadAdmin(Integer pageIndex, Integer limit, String key,String flag,Integer teacher_id,Integer baby_id,LogPage page, HttpServletResponse response,HttpSession session)
			throws Exception {
		session.setAttribute("flag",flag);
		page.setCurrentPage(pageIndex + 1);
		page.setPageSize(limit);
		page.setBaby_id(baby_id);
		page.setTeacher_id(teacher_id);
		List<Log> logs = null;
		Map<String, Object> map = new HashMap<String, Object>();
		boolean check = false;
		try {
			if(flag!=null &&flag.equals("0")){
				check = Authority.hasAuthority("/log/stu_loadLog.do");
			}else{
				check = Authority.hasAuthority("/log/tea_loadLog.do");
			}
			if (check == false) {
				map.put("hasError", true);
				map.put("error", "没有该操作权限");
				return;
			} else {
				Integer rows = service.getRows(page);
				page.setRows(rows);
				logs = service.getLogByPage(page);
				map.put("rows", logs);
				map.put("results", rows);
				map.put("hasError", false);
				map.put("error", "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("hasError", true);
			map.put("error", "error");
			map.put("rows", "");
			map.put("results", 0);
		} finally {
			JSONObject object = JSONObject.fromObject(map);
			ResponseUtil.write(response, object);
			logs = null;
			map = null;
			object = null;
		}
	}
	@RequestMapping("/deleteLog.do")
	public void deleteLog(String  ids,HttpServletResponse response,HttpSession session)throws Exception{
		String flag=(String) session.getAttribute("flag");
		service.deleteLog(ids,response,flag);
	}
	@RequestMapping("/updLog.do")
	public void updateLog(Log log,HttpServletResponse response,HttpSession session)throws Exception{
		String flag=(String) session.getAttribute("flag");
		service.updateLog(log,response,flag);
	}
	@RequestMapping("/changeState.do")
	public void changeState(Integer state,Integer id,HttpServletResponse response,HttpSession session) throws Exception{
		boolean check = false;
		String flag=(String) session.getAttribute("flag");
		ReturnInfo returnInfo=new ReturnInfo();
		try {
			if(flag!=null &&flag.equals("0")){
				check = Authority.hasAuthority("/log/stu_changeState.do");
			}else{
				check = Authority.hasAuthority("/log/tea_changeState.do");
			}
			if (check == false) {
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("没有该操作权限");
				return;
			} else {
				if(state.equals(1)){
					state=0;
				}else{
					state=1;
				}
				Log log=new Log();
				log.setId(id);
				log.setState(state);
				log.setFlag(1);
				service.updateLog(log);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
			 
		} finally {
			JSONObject object = JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			 flag=null;
			 returnInfo=null;
		}
	}
	@RequestMapping("/getLogById.do")
	public void getLogById(Integer id,HttpServletResponse response) throws Exception{
		Log log=service.getLogById(id);
		JSONObject object=JSONObject.fromObject(log);
		ResponseUtil.write(response, object);
	}
	@RequestMapping("/getLogByBabyId.do")
	public void getLogByBabyId(Integer baby_id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		if(cookie!=null){
			String str=cookie.getValue();
			JSONObject obj=JSONObject.fromObject(str);
			Baby b=(Baby) JSONObject.toBean(obj, Baby.class);
			if(baby_id==null){
				baby_id=b.getId();
			}
		}
		List<Log> logs=service.getLogsByBabyId(baby_id);
		JSONArray arr=JSONArray.fromObject(logs);
		ResponseUtil.write(response, arr);
	}
	@RequestMapping("/getLogByBabyId1.do")
	public void getLogByBabyId1(Integer baby_id,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Cookie cookie = CookieUtil.getCookieByName(request, "baby");
		if(cookie!=null){
			String str=cookie.getValue();
			JSONObject obj=JSONObject.fromObject(str);
			Baby b=(Baby) JSONObject.toBean(obj, Baby.class);
			if(baby_id==null){
				baby_id=b.getId();
			}
		}
		List<Log> logs=service.getLogsByBabyId1(baby_id);
		JSONArray arr=JSONArray.fromObject(logs);
		ResponseUtil.write(response, arr);
	}
	@RequestMapping("/updateLog.do")
	public void updateLog(Log log,HttpServletResponse response) throws Exception{
		service.updateLog(log,response);
	}
	@RequestMapping("/addLog.do")
	public void addLog(Log log,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Cookie cookie = CookieUtil.getCookieByName(request, "teacher");
		String str=cookie.getValue();
		JSONObject obj=JSONObject.fromObject(str);
		Teacher t=(Teacher) JSONObject.toBean(obj, Teacher.class);
		log.setTeacher_id(t.getId());
		service.addLog(log,response);
		logId=log.getId();
		LogTask task=new LogTask();
		Timer timer=new Timer();
		timer.schedule(task, 172800000);
	}
	class LogTask extends TimerTask{
		@Override
		public void run() {
			Log log=service.getLogById(logId);
			if(log.getFlag()==0){
				log.setState(1);
				log.setFlag(1);
				service.updateLog(log);
			}
		}
		
	}
}
