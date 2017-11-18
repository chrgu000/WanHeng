package com.to.serviceImpl;


import com.to.dao.ShareHouseDao;
import com.to.entity.ShareHouse;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.ShareHouseService;
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

@Service("shareHouseService")
@Transactional
public class ShareHouseServiceImpl implements ShareHouseService {
@Resource
private ShareHouseDao dao;
	public List<ShareHouse> getShareHouseByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateShareHouse(ShareHouse shareHouse, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {   shareHouse.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(shareHouse);
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

	public void addShareHouse(ShareHouse shareHouse, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {

			    shareHouse.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			    shareHouse.setCreateTime(new Timestamp(System.currentTimeMillis()));
			    shareHouse.setDelflag((short)2);
				dao.save(shareHouse);
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
		}
	}

	public void deleteShareHouse(String ids, HttpServletResponse response)
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

	public ShareHouse getShareHouseById(Integer id) {
		return dao.getById(id);
	}

	public List<ShareHouse> getAllShareHouse() {
		return dao.getAllShareHouse();
	}

}
