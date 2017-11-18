package com.cgwas.spaceOrder.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.spaceOrder.dao.api.ISpaceOrderDao;
import com.cgwas.spaceOrder.entity.SpaceOrder;
import com.cgwas.spaceOrder.entity.SpaceOrderVo;
import com.cgwas.spaceOrder.service.api.ISpaceOrderService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class SpaceOrderService implements ISpaceOrderService {
	private ISpaceOrderDao spaceOrderDao;
	public Serializable save(SpaceOrder spaceOrder){
		spaceOrder.setCreate_time(new Date());
		return spaceOrderDao.save(spaceOrder);
	}

	public void delete(SpaceOrder spaceOrder){
		spaceOrderDao.delete(spaceOrder);
	}
	
	public void deleteByExample(SpaceOrder spaceOrder){
		spaceOrderDao.deleteByExample(spaceOrder);
	}

	public void update(SpaceOrder spaceOrder){
		spaceOrderDao.update(spaceOrder);
	}
	
	public void updateIgnoreNull(SpaceOrder spaceOrder){
		spaceOrderDao.updateIgnoreNull(spaceOrder);
	}
		
	public void updateByExample(SpaceOrder spaceOrder){
		spaceOrderDao.update("updateByExampleSpaceOrder", spaceOrder);
	}

	public SpaceOrderVo load(SpaceOrder spaceOrder){
		return (SpaceOrderVo)spaceOrderDao.reload(spaceOrder);
	}
	
	public SpaceOrderVo selectForObject(SpaceOrder spaceOrder){
		return (SpaceOrderVo)spaceOrderDao.selectForObject("selectSpaceOrder",spaceOrder);
	}
	
	public Integer getSumSpace(SpaceOrder spaceOrder){
		return (Integer)spaceOrderDao.selectForObject("getSumSpace",spaceOrder);
	}
	
	@SuppressWarnings("unchecked")
	public List<SpaceOrderVo> selectForList(SpaceOrder spaceOrder){
		return (List<SpaceOrderVo>)spaceOrderDao.selectForList("selectSpaceOrder",spaceOrder);
	}
	
	public Page page(Page page, SpaceOrder spaceOrder) {
		return spaceOrderDao.page(page, spaceOrder);
	}

	@Autowired
	public void setISpaceOrderDao(
			@Qualifier("spaceOrderDao") ISpaceOrderDao  spaceOrderDao) {
		this.spaceOrderDao = spaceOrderDao;
	}

}
