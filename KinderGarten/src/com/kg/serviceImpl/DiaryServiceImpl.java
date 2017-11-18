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

import com.kg.dao.DiaryDao;
import com.kg.entity.Diary;
import com.kg.entity.DiaryType;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.DiaryPage;
import com.kg.service.DiaryService;
import com.kg.util.FileUtil;
import com.kg.util.ResponseUtil;

@Service("diaryService")
@Transactional
public class DiaryServiceImpl implements DiaryService {
	@Resource
	private DiaryDao dao;

	public void addDiary(Diary diary, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo = new ReturnInfo();
		JSONObject object = null;
		try {
			dao.addDiary(diary);
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
	public void deleteByIds(String ids, HttpServletResponse response,HttpSession session)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		String url = session.getServletContext().getRealPath("");
		try {
				Map<String,String[]> map=new HashMap<String,String[]>();
				map.put("ids", ids.split(","));
				List<Diary> diarys=dao.getDiaryByIds(map);
				for (Diary diary : diarys) {
					String path1 = diary.getPath();
					String path2 = diary.getMin_path();
					String p1 = url + path1.substring(1, path1.length());
					String p2 = url + path2.substring(1, path2.length());
					FileUtil.deleteFile(p1);
					FileUtil.deleteFile(p2);
				}
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

	public List<DiaryType> findAllDiaryType() {
		return dao.findAllDiaryType();
	}

	public List<Diary> findDiaryByMap(Map<String, Object> map) {
		return dao.findDiaryByMap(map);
	}

	public List<Diary> getDiaryByPage(DiaryPage page) {
		return dao.getDiaryByPage(page);
	}

	public Integer getRows(DiaryPage page) {
		return dao.getRows(page);
	}
}
