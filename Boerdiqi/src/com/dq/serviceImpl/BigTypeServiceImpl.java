package com.dq.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.BigTypeDao;
import com.dq.entity.BigType;
import com.dq.entity.util.ReturnInfo;
import com.dq.page.Page;
import com.dq.service.BigTypeService;
import com.dq.util.ResponseUtil;

@Service("bigTypeService")
@Transactional
public class BigTypeServiceImpl implements BigTypeService {
	@Resource 
private BigTypeDao dao;
	public void add(BigType bigtype, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
				dao.save(bigtype);
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

	public void delete(String ids, HttpServletResponse response)
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
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("该信息有级联数据,不能删除");
		}finally{
			object=JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;
		}		
		
	}

	public BigType getById(Integer id) {
		return dao.getById(id);
	}

	public List<BigType> getByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void update(BigType bigtype, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
				dao.update(bigtype);
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

	public List<BigType> getAllBigType() {
		return dao.getAllBigType();
	}

 

}
