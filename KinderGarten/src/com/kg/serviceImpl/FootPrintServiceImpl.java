package com.kg.serviceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kg.dao.FootprintDao;
import com.kg.entity.Footprint;
import com.kg.entity.util.ReturnInfo;
import com.kg.page.Page;
import com.kg.service.FootPrintService;
import com.kg.util.Authority;
import com.kg.util.ResponseUtil;
@Service("footprintService")
@Transactional
public class FootPrintServiceImpl implements FootPrintService {
	@Resource
	private FootprintDao dao;
	public void deleteFootprint(String ids, HttpServletResponse response,String flag)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		boolean check=false;
		try {
			if(flag!=null &&flag.equals("0")){
				check = Authority.hasAuthority("/footprint/stu_deleteFootprint.do");
			}else{
				check = Authority.hasAuthority("/footprint/tea_deleteFootprint.do");
			}
			if(check==true){
				Map<String,String[]> map=new HashMap<String,String[]>();
				map.put("ids", ids.split(","));
				dao.deleteByIds(map);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			}else{
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("没有该操纵权限");
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

	public List<Footprint> getFootprintByPage(Page page) {
		return dao.getFootprintByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateFootprint(Footprint footprint,HttpServletResponse response,String flag) throws Exception{
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		boolean check=false;
		try {
			if(flag!=null &&flag.equals("0")){
				check = Authority.hasAuthority("/footprint/stu_updFootprint.do");
			}else{
				check = Authority.hasAuthority("/footprint/tea_updFootprint.do");
			}
			if(check==true){
				dao.updateFootprint(footprint);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
			}else{
				returnInfo.setHasError(true);
				returnInfo.setErrInfo("没有该操纵权限");
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
	public Footprint getFootprintById(Integer id) {
		return dao.getFootprintById(id);
	}

	public void updateFootprint(Footprint footprint) {
	dao.updateFootprint(footprint);
	}

	public List<Footprint> getFootprintByTeacherId(Map<String,String> map) {
		return dao.getFootprintByTeacherId(map);
	}

	public void updateFootprint(Footprint footprint,
			HttpServletResponse response) throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
				dao.updateFootprint(footprint);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("录入成功");
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

	public List<Footprint> getFootprintByBabyId(Integer id) {
		return dao.getFootprintByBabyId(id);
	}

	public List<Footprint> getFootprintByGardenId(Integer garden_id) {
		return dao.getFootprintByGardenId(garden_id);
	} 
}
