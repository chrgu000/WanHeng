package com.to.serviceImpl;


import com.to.dao.PriceDao;
import com.to.entity.Price;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.PriceService;
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

@Service("PriceService")
@Transactional
public class PriceServiceImpl implements PriceService {
@Resource
private PriceDao dao;
	public List<Price> getPriceByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updatePrice(Price price, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;

		try {   price.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(price);
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

	public void addPrice(Price price, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {

			    price.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			    price.setCreateTime(new Timestamp(System.currentTimeMillis()));
			    price.setDelflag((short)2);
				dao.save(price);
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

	public void deletePrice(String ids, HttpServletResponse response)
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

	public Price getPriceById(Integer id) {
		return dao.getById(id);
	}

	public List<Price> getAllPrice() {
		return dao.getAllPrice();
	}

}
