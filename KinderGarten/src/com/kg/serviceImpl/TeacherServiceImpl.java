package com.kg.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kg.dao.BabyDao;
import com.kg.dao.FootprintDao;
import com.kg.dao.LogDao;
import com.kg.dao.TeacherDao;
import com.kg.entity.Baby;
import com.kg.entity.Footprint;
import com.kg.entity.Log;
import com.kg.entity.Teacher;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.Page;
import com.kg.service.TeacherService;
import com.kg.util.FileUtil;
import com.kg.util.MD5;
import com.kg.util.ResponseUtil;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService {
	@Resource
	private TeacherDao dao;
    @Resource
    private BabyDao bdao;
    @Resource
    private LogDao ldao;
    @Resource
    private FootprintDao fdao;
	public void addTeacher(Teacher teacher) {
		dao.addTeacher(teacher);
	}

	public Teacher getTeacherById(Integer id) {
		return dao.getTeacherById(id);
	}

	public List<Teacher> getTeacherByPage(Page page) {
		return dao.getTeacherByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}


	public void updateTeacher(Teacher teacher) {
		dao.updateTeacher(teacher);
	}

	public void updateTeacher(Teacher teacher, HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
				dao.updateTeacher(teacher);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("保存成功");
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

	public void addTeacher(Teacher teacher, HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		MD5 md5=new MD5();
		try {
				teacher.setPassword(md5.getMD5ofStr(teacher.getPassword()));
				dao.addTeacher(teacher);
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
			md5=null;
		}
	}

	public void deleteTeacher(String ids, HttpServletResponse response,HttpSession session)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		String url = session.getServletContext().getRealPath("");
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("ids", ids.split(","));
		List<Teacher> teachers=dao.getTeacherByIds(map);
		for (Teacher teacher : teachers) {
			String path = teacher.getHeader_pic_path();
			if(path!=null){
				String p = url + path.substring(1, path.length());
				FileUtil.deleteFile(p);
			}
		}
		try {
			List<Baby> babys=bdao.getBabyByTeacher(map);
			List<Log> logs=ldao.getLogByTeacherIds(map);
			List<Footprint> footprints=fdao.getFootprintTeacherIds(map);
			if ((logs== null || logs.size() == 0)
					&& (babys == null || babys.size() == 0)&&(footprints==null||footprints.size()==0)) {
					dao.deleteByIds(map);
					returnInfo.setHasError(false);
					returnInfo.setErrInfo("");
			}else{
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("该教师有学生,日志,足迹级联数据,无法删除");
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

	public List<Teacher> getTeachersByGardenId(Integer id) {
		return dao.getTeachersByGardenId(id);
	}

	public List<Teacher> findAllTeacher() {
		return dao.findAllTeacher();
	}

	public void regist(Teacher teacher) {
		dao.addTeacher(teacher);
	}

	public Teacher login(Teacher teacher) {
		return dao.login(teacher);
	}

	public void updatePwdByTel(Teacher teacher) {
		dao.updatePwdByTel(teacher);
	}

	public Teacher getTeacher(Integer id) {
		return dao.getTeacher(id);
	}
}