package com.cgwas.userGrowth.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userGrowth.dao.api.IUserGrowthDao;
import com.cgwas.userGrowth.entity.CompanyForGrowth;
import com.cgwas.userGrowth.entity.UserForGrowth;
import com.cgwas.userGrowth.entity.UserGrowth;
import com.cgwas.userGrowth.entity.UserGrowthVo;
import com.cgwas.userGrowth.service.api.IUserGrowthService;

@Service
public class UserGrowthService implements IUserGrowthService {
	private IUserGrowthDao userGrowthDao;

	public Serializable save(UserGrowth userGrowth) {
		return userGrowthDao.save(userGrowth);
	}

	public void delete(UserGrowth userGrowth) {
		userGrowthDao.delete(userGrowth);
	}

	public void deleteByExample(UserGrowth userGrowth) {
		userGrowthDao.deleteByExample(userGrowth);
	}

	public void update(UserGrowth userGrowth) {
		userGrowthDao.update(userGrowth);
	}

	public void updateIgnoreNull(UserGrowth userGrowth) {
		userGrowthDao.updateIgnoreNull(userGrowth);
	}

	public void updateByExample(UserGrowth userGrowth) {
		userGrowthDao.update("updateByExampleUserGrowth", userGrowth);
	}

	public UserGrowthVo load(UserGrowth userGrowth) {
		return (UserGrowthVo) userGrowthDao.reload(userGrowth);
	}

	public UserGrowthVo selectForObject(UserGrowth userGrowth) {
		return (UserGrowthVo) userGrowthDao.selectForObject("selectUserGrowth",
				userGrowth);
	}

	public List<UserGrowthVo> selectForList(UserGrowth userGrowth) {
		return (List<UserGrowthVo>) userGrowthDao.selectForList(
				"selectUserGrowth", userGrowth);
	}

	public Page page(Page page, UserGrowth userGrowth) {
		return userGrowthDao.page(page, userGrowth);
	}

	@Autowired
	public void setIUserGrowthDao(
			@Qualifier("userGrowthDao") IUserGrowthDao userGrowthDao) {
		this.userGrowthDao = userGrowthDao;
	}

	@Override
	public UserGrowth getUserGrowthByUserId(Long userId) {
		UserGrowth out = (UserGrowth) userGrowthDao.selectForObject(
				"getUserGrowthByUserId", userId);
		if(out==null){// 若为空则从新创建一个
			out = new UserGrowth();
			out.setFlat(0);
			out.setPrestige(0);
			out.setUser_id(userId);
			userGrowthDao.save(out);
		}
		
		return out;
	}

	public void reduceOrAddGrowth(String action, Integer GrowthNum, Long userId,Integer prestige) {
		Integer changeGrowthNum = GrowthNum; // 改变的积分数量
		UserGrowth userGrowth = new UserGrowth();
		userGrowth=this.getUserGrowthByUserId(userId);// 重置
		
//		userGrowth.setUser_id(userId);
		if ("reduce".equals(action)) { // 积分减
			if (userGrowth.getFlat()-changeGrowthNum<=0) {
				changeGrowthNum=0;
			} else {
				changeGrowthNum =-changeGrowthNum;
			}
			if (userGrowth.getPrestige()-prestige<=0) {
				userGrowth.setPrestige(0);
			} else {
				prestige =userGrowth.getPrestige()-prestige;
				userGrowth.setPrestige(prestige);
			}
			
		} else if ("clear".equals(action)) { // 积分清零
			userGrowthDao.update("cleanGrowth", userGrowth);
			return;
		}

		userGrowth.setFlat(changeGrowthNum);
//		userGrowth.setPrestige(prestige);
		userGrowthDao.update("reduceOrAddGrowth", userGrowth);
	}

	@Override
	public List<UserForGrowth> getUserListForGrowth(UserForGrowth growth,
			Page page) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("userGrowth", growth);
		map.put("page", page);

		return (List<UserForGrowth>) userGrowthDao.selectForList(
				"getUserListForGrowth", map);
	}

	@Override
	public List<CompanyForGrowth> getCompanyListForGrowth(
			CompanyForGrowth companyForGrowth, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyForGrowth", companyForGrowth);
		map.put("page", page);
		return (List<CompanyForGrowth>) userGrowthDao.selectForList(
				"getCompanyListForGrowth", map);
	}

	@Override
	public Long getUserListForGrowthCount(UserForGrowth growth) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userGrowth", growth);
		return (Long) userGrowthDao.selectForObject(
				"getUserListForGrowthCount", map);
	}

	@Override
	public Long getCompanyListForGrowthCount(CompanyForGrowth companyForGrowth) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyForGrowth", companyForGrowth);
		return (Long) userGrowthDao.selectForObject(
				"getCompanyListForGrowthCount", map);
	}

}
