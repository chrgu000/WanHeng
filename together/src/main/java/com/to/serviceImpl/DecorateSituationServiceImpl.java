package com.to.serviceImpl;


import com.to.dao.DecorateSituationDao;
import com.to.entity.DecorateSituation;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.DecorateSituationService;
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

@Service("DecorateSituationService")
@Transactional
public class DecorateSituationServiceImpl implements DecorateSituationService{
@Resource
private DecorateSituationDao dao;
	public List<DecorateSituation> getDecorateSituationByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateDecorateSituation(DecorateSituation decorateSituation, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {   decorateSituation.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(decorateSituation);
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

	public void addDecorateSituation(DecorateSituation decorateSituation, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
			    decorateSituation.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			    decorateSituation.setCreateTime(new Timestamp(System.currentTimeMillis()));
			    decorateSituation.setDelflag((short)2);
				dao.save(decorateSituation);
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

	public void deleteDecorateSituation(String ids, HttpServletResponse response)
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

	public DecorateSituation getDecorateSituationById(Integer id) {
		return dao.getById(id);
	}

	public List<DecorateSituation> getAllDecorateSituation() {
		return dao.getAllDecorateSituation();
	}
}
