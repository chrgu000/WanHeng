package com.cgwas.recommend.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.recommend.dao.api.IRecommendDao;
import com.cgwas.recommend.entity.CompanyRecommend;
import com.cgwas.recommend.entity.Recommend;
import com.cgwas.recommend.entity.RecommendVo;
import com.cgwas.recommend.entity.UserRecommend;
import com.cgwas.recommend.service.api.IRecommendService;

@Service
public class RecommendService implements IRecommendService {
	private IRecommendDao recommendDao;

	public Serializable save(Recommend recommend) {
		return recommendDao.save(recommend);
	}

	public void delete(Recommend recommend) {
		recommendDao.delete(recommend);
	}

	public void deleteByExample(Recommend recommend) {
		recommendDao.deleteByExample(recommend);
	}

	public void update(Recommend recommend) {
		recommendDao.update(recommend);
	}

	public void updateIgnoreNull(Recommend recommend) {
		recommendDao.updateIgnoreNull(recommend);
	}

	public void updateByExample(Recommend recommend) {
		recommendDao.update("updateByExampleRecommend", recommend);
	}

	public RecommendVo load(Recommend recommend) {
		return (RecommendVo) recommendDao.reload(recommend);
	}

	public RecommendVo selectForObject(Recommend recommend) {
		return (RecommendVo) recommendDao.selectForObject("selectRecommend",
				recommend);
	}

	public List<RecommendVo> selectForList(Recommend recommend) {
		return (List<RecommendVo>) recommendDao.selectForList(
				"selectRecommend", recommend);
	}

	public Page page(Page page, Recommend recommend) {
		return recommendDao.page(page, recommend);
	}

	@Autowired
	public void setIRecommendDao(
			@Qualifier("recommendDao") IRecommendDao recommendDao) {
		this.recommendDao = recommendDao;
	}

	@Override
	public void updateUserAndCompanyRecommend(Recommend recommend) {
		recommendDao.update("updateUserAndCompanyRecommend", recommend);
	}

	@Override
	public List<UserRecommend> getUserListForRecommend(
			UserRecommend userRecommend, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("userRecommend", userRecommend);
		map.put("page", page);

		return (List<UserRecommend>) recommendDao.selectForList(
				"getUserListForRecommend", map);
	}

	@Override
	public List<CompanyRecommend> getCompanyListForRecommend(Page page,
			CompanyRecommend companyRecommend) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("companyRecommend", companyRecommend);
		map.put("page", page);

		return (List<CompanyRecommend>) recommendDao.selectForList(
				"getCompanyListForRecommend", map);
	}

	@Override
	public Long getUserListForRecommendCount(UserRecommend userecommend) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("userRecommend", userecommend);
		return (Long) recommendDao.selectForObject(
				"getUserListForRecommendCount", map);
	}

	@Override
	public Long getCompanyListForRecommendCount(
			CompanyRecommend companyRecommend) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("companyRecommend", companyRecommend);

		return (Long) recommendDao.selectForObject(
				"getCompanyListForRecommendCount", map);
	}

	@Override
	public Recommend getRecommendById(Recommend recommend) {
		return (Recommend) recommendDao.selectForObject("getRecommendById",
				recommend);
	}

}
