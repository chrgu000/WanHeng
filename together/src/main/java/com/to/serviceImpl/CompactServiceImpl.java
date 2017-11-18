package com.to.serviceImpl;


import com.to.dao.CompactDao;
import com.to.entity.*;
import com.to.entity.util.ReturnInfo;
import com.to.page.Page;
import com.to.service.CompactService;
import com.to.util.CompactUtil;
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

@Service("CompactService")
@Transactional
public class CompactServiceImpl implements CompactService {
@Resource
private CompactDao dao;
	public List<Compact> getCompactByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

	public void updateCompact(Compact compact, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {   compact.setModifiedTime(new Timestamp(System.currentTimeMillis()));
				dao.update(compact);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("保存成功");
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object= JSONObject.fromObject(returnInfo);
			ResponseUtil.write(response, object);
			returnInfo=null;
			object=null;

		}		
	}

	public void addCompact(Compact compact, HttpServletResponse response)
			throws Exception {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
                compact.setCompactNum(System.currentTimeMillis()+"");
			    compact.setModifiedTime(new Timestamp(System.currentTimeMillis()));
			    compact.setCreateTime(new Timestamp(System.currentTimeMillis()));
			    compact.setDelflag((short)2);
			    compact.setStatus((short)1);
			    compact.setState((short)0);
				dao.save(compact);
				returnInfo.setHasError(false);
				returnInfo.setErrInfo("添加成功");
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

	public void deleteCompact(String ids, HttpServletResponse response)
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
	public List<Compact> getCompactByStatus(Map<String, Integer> map) {
		return dao.getCompactByStatus(map);
	}

	@Override
	public void getCompactByHouseId(Integer houseId, HttpServletResponse response) {
		ReturnInfo returnInfo =new ReturnInfo();
		JSONObject object=null;
		try {
			Compact c=dao.getCompactByHouseId(houseId);
			Short number=c.getPayWay().getNumber();
			String t1=c.getStartTime();
			String t2=c.getEndTime();
			c.setPayTimes(CompactUtil.getPayTimes(t1,t2,number));
			object=JSONObject.fromObject(c);
			ResponseUtil.write(response,object);
		} catch (Exception e) {
			e.printStackTrace();
			returnInfo.setHasError(true);
			returnInfo.setErrInfo("系统异常");
		}finally{
			object=JSONObject.fromObject(returnInfo);
		}
	}

	@Override
	public void addGas(Gas gas) {
		dao.addGas(gas);
	}

	@Override
	public void addCondo(Condo condo) {
		dao.addCondo(condo);
	}

	@Override
	public void addWater(Water water) {
		dao.addWater(water);
	}

	@Override
	public void addPower(Power power) {
		dao.addPower(power);
	}

	@Override
	public void updatePower(Fee fee) {
		dao.updatePower(fee);
	}

	@Override
	public void updateCondo(Fee fee) {
		dao.updateCondo(fee);
	}

	@Override
	public void updateGas(Fee fee) {
        dao.updateGas(fee);
	}

	@Override
	public void updateWater(Fee fee) {
          dao.updateWater(fee);
	}

	@Override
	public List<Compact> getCompactByStatusAndUser(Map<String,String> map) {
		return dao.getCompactByStatusAndUser(map);
	}

	@Override
	public List<Price> getAllPrice() {
		return dao.getAllPrice();
	}

	public Compact getCompactById(Integer id) {
		return dao.getById(id);
	}



}
