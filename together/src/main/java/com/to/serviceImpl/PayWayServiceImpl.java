package com.to.serviceImpl;


import com.to.dao.PayWayDao;
import com.to.entity.PayWay;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.PayWayService;
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

@Service("PayWayService")
@Transactional
public class PayWayServiceImpl implements PayWayService {
@Resource
private PayWayDao dao;
	public List<PayWay> getPayWayByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updatePayWay(PayWay payWay, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;

		try {   payWay.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(payWay);
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

	public void addPayWay(PayWay payWay, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {

			    payWay.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			    payWay.setCreateTime(new Timestamp(System.currentTimeMillis()));
			    payWay.setDelflag((short)2);
				dao.save(payWay);
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

	public void deletePayWay(String ids, HttpServletResponse response)
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

	public PayWay getPayWayById(Integer id) {
		return dao.getById(id);
	}

	public List<PayWay> getAllPayWay() {
		return dao.getAllPayWay();
	}

}
