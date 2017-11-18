package com.to.serviceImpl;


import com.to.dao.RentalInformationDao;
import com.to.entity.RentalInformation;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.RentalInformationService;
import com.to.util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("RentalInformationService")
@Transactional
public class RentalInformationServiceImpl implements RentalInformationService {
@Resource
private RentalInformationDao dao;
	public List<RentalInformation> getRentalInformationByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateRentalInformation(RentalInformation rentalInformation, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;

		try {   rentalInformation.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(rentalInformation);
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

	public void addRentalInformation(RentalInformation rentalInformation, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {

			    rentalInformation.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			    rentalInformation.setCreateTime(new Timestamp(System.currentTimeMillis()));
			    rentalInformation.setDelflag((short)2);
				dao.save(rentalInformation);
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

	public void deleteRentalInformation(String ids, HttpServletResponse response)
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
	public void getAllRentalInformations(HttpServletResponse response)throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONArray arrs=null;
		List<RentalInformation> rentalInformations=null;
		try {
			rentalInformations=dao.getAllRentalInformations();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			arrs=JSONArray.fromObject(rentalInformations);
			ResponseUtil.write(response, arrs);
			returnInfo=null;
			arrs=null;
		}
	}

	public RentalInformation getRentalInformationById(Integer id) {
		return dao.getById(id);
	}

	public List<RentalInformation> getAllRentalInformation() {
		return dao.getAllRentalInformation();
	}

}
