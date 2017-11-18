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
import com.kg.dao.PictureDao;
import com.kg.entity.Baby;
import com.kg.entity.Footprint;
import com.kg.entity.Log;
import com.kg.entity.Picture;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.Page;
import com.kg.service.BabyService;
import com.kg.util.FileUtil;
import com.kg.util.MD5;
import com.kg.util.ResponseUtil;

@Service("babyService")
@Transactional
public class BabyServiceImpl implements BabyService {
	@Resource
	private BabyDao dao;
    @Resource
    private LogDao ldao;
    @Resource
    private PictureDao pdao;
    @Resource
    private FootprintDao fdao;
	public void addBaby(Baby baby) {
		dao.addBaby(baby);
	}

	public Baby getBabyById(Integer id) {
		return dao.getBabyById(id);
	}

	public List<Baby> getBabyByPage(Page page) {
		return dao.getBabyByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateBaby(Baby baby) {
		dao.updateBaby(baby);
	}

	public void updateBaby(Baby baby, HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
				dao.updateBaby(baby);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("修改成功");
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

	public void addBaby(Baby baby, HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		MD5 md5=new MD5();
		try {
				baby.setPassword(md5.getMD5ofStr(baby.getPassword()));
				dao.addBaby(baby);
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

	public void deleteBaby(String ids, HttpServletResponse response,HttpSession session)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		String url = session.getServletContext().getRealPath("");
		Map<String,String[]> map=new HashMap<String,String[]>();
		map.put("ids", ids.split(","));
		List<Baby> babys=dao.getBabyByIds(map);
		for (Baby baby : babys) {
			String path = baby.getHeader_pic_path();
			if(path!=null){
				String p = url + path.substring(1, path.length());
				FileUtil.deleteFile(p);
			}
		}
		try {
			List<Log> logs=ldao.getLogByBabyIds(map);
			List<Picture> pictures=pdao.getPictureByBabyIds(map);
			List<Footprint> footprints=fdao.getFootprintBabyIds(map);
			if((logs==null||logs.size()==0)&&(pictures==null||pictures.size()==0)||(footprints!=null&&footprints.size()==0)){
					dao.deleteByIds(map);
					returnInfo.setHasError(false);
					returnInfo.setErrInfo("");
			}else{
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("该学生有日志,照片,足迹级联数据,无法删除");
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

	public List<Baby> findAllBaby() {
		return dao.findAllBaby();
	}

	public void regist(Baby baby) {
		dao.addBaby(baby);
	}

	public Baby login(Baby baby) {
		return dao.login(baby);
	}

	public void updatePwdByTel(Baby baby) {
	   dao.updatePwdByTel(baby);
		
	}

	public Baby getBaby(Integer id) {
		return dao.getBaby(id);
	}

	 
}