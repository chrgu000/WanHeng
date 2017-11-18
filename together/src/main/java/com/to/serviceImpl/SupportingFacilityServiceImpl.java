package com.to.serviceImpl;


import com.to.dao.SupportingFacilityDao;
import com.to.entity.SupportingFacility;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.SupportingFacilityService;
import com.to.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("SupportingFacilityService")
@Transactional
public class SupportingFacilityServiceImpl implements SupportingFacilityService {
@Resource
private SupportingFacilityDao dao;
	public List<SupportingFacility> getSupportingFacilityByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateSupportingFacility(SupportingFacility supportingFacility, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;

		try {  supportingFacility.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(supportingFacility);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("");
		} catch (Exception e) {
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object= JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;

		}		
	}

	public void addSupportingFacility(SupportingFacility supportingFacility, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {

			    supportingFacility.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			    supportingFacility.setCreateTime(new Timestamp(System.currentTimeMillis()));
			    supportingFacility.setDelflag((short)2);
				dao.save(supportingFacility);
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

	public void deleteSupportingFacility(String ids, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;

		try {
			Map<String,String[]> map=new HashMap<String,String[]>();
			String[] Ids=ids.split(",");
			map.put("ids", Ids);
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

	@Override
	public List<SupportingFacility> getSupportingFacilityByHouseId(Integer houseId) {
		return dao.getAllSupportingFacilityByHouseId(houseId);
	}

	public SupportingFacility getSupportingFacilityById(Integer id) {
		return dao.getById(id);
	}

	public List<SupportingFacility> getAllSupportingFacility() {
		return dao.getAllSupportingFacility();
	}

}
